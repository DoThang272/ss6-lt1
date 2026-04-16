package re.edu.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import re.edu.dto.request.TicketRequest;
import re.edu.dto.response.ApiResponse;
import re.edu.dto.response.TicketResponse;
import re.edu.entity.ParkingTicket;
import re.edu.entity.Vehicle;
import re.edu.entity.Zone;
import re.edu.repository.ParkingTicketRepository;
import re.edu.repository.VehicleRepository;
import re.edu.repository.ZoneRepository;
import re.edu.service.ParkingService;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ParkingServiceImpl implements ParkingService {

    private final VehicleRepository vehicleRepository;
    private final ZoneRepository zoneRepository;
    private final ParkingTicketRepository parkingTicketRepository;

    @Override
    @Transactional
    public ApiResponse<TicketResponse> checkIn(TicketRequest request) {
        Vehicle vehicle = vehicleRepository.findById(request.getVehicleId())
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));

        Zone zone = zoneRepository.findById(request.getZoneId())
                .orElseThrow(() -> new RuntimeException("Zone not found"));

        if (zone.getOccupiedSpots() >= zone.getCapacity()) {
            throw new RuntimeException("Zone is full");
        }

        Page<ParkingTicket> activeTicketPage =
                parkingTicketRepository.findActiveTicketsByVehicleId(vehicle.getId(), PageRequest.of(0, 1));

        if (!activeTicketPage.getContent().isEmpty()) {
            throw new RuntimeException("Vehicle is already checked in");
        }

        ParkingTicket ticket = ParkingTicket.builder()
                .vehicle(vehicle)
                .zone(zone)
                .checkInTime(LocalDateTime.now())
                .build();

        zone.setOccupiedSpots(zone.getOccupiedSpots() + 1);

        ParkingTicket savedTicket = parkingTicketRepository.save(ticket);
        zoneRepository.save(zone);

        TicketResponse response = TicketResponse.builder()
                .id(savedTicket.getId())
                .licensePlate(savedTicket.getVehicle().getLicensePlate())
                .zoneName(savedTicket.getZone().getName())
                .checkInTime(savedTicket.getCheckInTime())
                .typeVehicle(savedTicket.getVehicle().getVehicleType())
                .checkOutTime(savedTicket.getCheckOutTime())
                .build();

        return ApiResponse.<TicketResponse>builder()
                .success(true)
                .message("Checked in successfully")
                .data(response)
                .build();
    }

    @Override
    @Transactional
    public ApiResponse<TicketResponse> checkOut(Long vehicleId) {
        Page<ParkingTicket> activeTicketPage =
                parkingTicketRepository.findActiveTicketsByVehicleId(vehicleId, PageRequest.of(0, 1));

        ParkingTicket ticket = activeTicketPage.getContent().stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No active parking ticket found for this vehicle"));

        ticket.setCheckOutTime(LocalDateTime.now());

        Zone zone = ticket.getZone();
        if (zone.getOccupiedSpots() > 0) {
            zone.setOccupiedSpots(zone.getOccupiedSpots() - 1);
        }

        ParkingTicket updatedTicket = parkingTicketRepository.save(ticket);
        zoneRepository.save(zone);

        TicketResponse response = TicketResponse.builder()
                .id(updatedTicket.getId())
                .licensePlate(updatedTicket.getVehicle().getLicensePlate())
                .zoneName(updatedTicket.getZone().getName())
                .typeVehicle(updatedTicket.getVehicle().getVehicleType())
                .checkInTime(updatedTicket.getCheckInTime())
                .checkOutTime(updatedTicket.getCheckOutTime())
                .build();

        return ApiResponse.<TicketResponse>builder()
                .success(true)
                .message("Checked out successfully")
                .data(response)
                .build();
    }
}