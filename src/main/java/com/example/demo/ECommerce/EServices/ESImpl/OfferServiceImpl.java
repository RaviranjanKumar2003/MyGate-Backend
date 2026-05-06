package com.example.demo.ECommerce.EServices.ESImpl;

import com.example.demo.ECommerce.Dtos.OfferDto;
import com.example.demo.ECommerce.ERepositories.ProductRepository;
import com.example.demo.ECommerce.Eentities.Offer;
import com.example.demo.ECommerce.ERepositories.OfferRepository;
import com.example.demo.ECommerce.EServices.OfferService;
import com.example.demo.ECommerce.Eentities.Product;
import com.example.demo.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OfferServiceImpl implements OfferService {

    private final OfferRepository offerRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private ProductRepository productRepository;

    public OfferServiceImpl(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }

    /* ================= DTO MAPPER ================= */
    private OfferDto mapToDto(Offer offer) {

        OfferDto dto = new OfferDto(
                offer.getId(),
                offer.getRefId(),
                offer.getBuyerId(),
                offer.getOfferPrice(),
                offer.getOfferTime()
        );

        dto.setRefType(offer.getRefType());
        dto.setStatus(offer.getStatus());

        // 🔥 Buyer Details
        if (offer.getBuyerId() != null) {
            userRepository.findById(offer.getBuyerId()).ifPresent(user -> {
                dto.setBuyerName(user.getName());
                dto.setBuyerEmail(user.getEmail());
                dto.setBuyerMobile(user.getMobileNumber());
            });
        }

        return dto;
    }

    private Offer mapToEntity(OfferDto dto) {

        Offer offer = new Offer();

        offer.setId(dto.getId());
        offer.setRefId(dto.getRefId());
        offer.setRefType(dto.getRefType());
        offer.setBuyerId(dto.getBuyerId());
        offer.setOfferPrice(dto.getOfferPrice());
        offer.setOfferTime(dto.getOfferTime());
        offer.setStatus(dto.getStatus());

        return offer;
    }

    /* ================= CREATE OFFER ================= */
    @Override
    public OfferDto createOffer(OfferDto offerDto) {

        if (offerDto.getOfferPrice() == null) {
            throw new RuntimeException("Offer price is required");
        }

        if (offerDto.getRefId() == null || offerDto.getRefType() == null) {
            throw new RuntimeException("refId and refType are required");
        }

        Offer offer = mapToEntity(offerDto);

        // Default values
        if (offer.getOfferTime() == null) {
            offer.setOfferTime(LocalDateTime.now());
        }

        if (offer.getStatus() == null) {
            offer.setStatus("PENDING"); // PENDING / ACCEPTED / REJECTED
        }

        Offer saved = offerRepository.save(offer);

        /* ================= NOTIFICATION ================= */
        try {
            if ("PRODUCT".equalsIgnoreCase(offer.getRefType())) {

                Product product = productRepository.findById(offer.getRefId())
                        .orElseThrow(() -> new RuntimeException("Product not found"));

                Long sellerId = product.getSeller().getId();

                messagingTemplate.convertAndSend(
                        "/topic/offer/" + sellerId,
                        "New Offer on product: " + product.getTitle()
                );

            } else if ("FLAT".equalsIgnoreCase(offer.getRefType())) {

                messagingTemplate.convertAndSend(
                        "/topic/flat-offer/" + offer.getRefId(),
                        "New Offer on Flat ID: " + offer.getRefId()
                );
            }
        } catch (Exception e) {
            // ❌ Notification fail ho jaye to bhi app crash na ho
            System.out.println("Notification failed: " + e.getMessage());
        }

        return mapToDto(saved);
    }

    /* ================= UPDATE OFFER ================= */
    @Override
    public OfferDto updateOffer(Integer id, OfferDto offerDto) {

        Offer offer = offerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Offer not found"));

        if (offerDto.getOfferPrice() != null) {
            offer.setOfferPrice(offerDto.getOfferPrice());
        }

        if (offerDto.getStatus() != null) {
            offer.setStatus(offerDto.getStatus());
        }

        Offer updated = offerRepository.save(offer);

        return mapToDto(updated);
    }

    /* ================= DELETE ================= */
    @Override
    public void deleteOffer(Integer id) {
        offerRepository.deleteById(id);
    }

    /* ================= GET BY ID ================= */
    @Override
    public OfferDto getOfferById(Integer id) {
        return offerRepository.findById(id)
                .map(this::mapToDto)
                .orElseThrow(() -> new RuntimeException("Offer not found"));
    }

    /* ================= GET ALL ================= */
    @Override
    public List<OfferDto> getAllOffers() {
        return offerRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    /* ================= PRODUCT OFFERS ================= */
    @Override
    public List<OfferDto> getOffersByProduct(Integer productId) {

        List<Offer> offers = offerRepository
                .findByRefIdAndRefType(Long.valueOf(productId), "PRODUCT");

        return offers.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }


    /* ================= BUYER OFFERS ================= */
    @Override
    public List<OfferDto> getOffersByBuyer(Integer buyerId) {

        List<Offer> offers = offerRepository.findByBuyerId(buyerId);

        return offers.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }


    @Override
    public List<OfferDto> getOffersByFlat(Long flatId) {
        List<Offer> offers = offerRepository
                .findByRefIdAndRefType(flatId, "FLAT");

        return offers.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }
}