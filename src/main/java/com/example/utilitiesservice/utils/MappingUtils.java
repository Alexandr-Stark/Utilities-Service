package com.example.utilitiesservice.utils;


import com.example.utilitiesservice.dto.UserBillDto;
import com.example.utilitiesservice.models.UtilityMeter;
import org.springframework.stereotype.Service;

@Service
public class MappingUtils {

    public UserBillDto mapToUserBillDto (UtilityMeter meter) {
        UserBillDto userBillDto = new UserBillDto();

        userBillDto.setUserId(meter.getBill().getResidence().getUser().getId());
        userBillDto.setUserName(meter.getBill().getResidence().getUser().getName());
        userBillDto.setUserSurname(meter.getBill().getResidence().getUser().getSurname());
        userBillDto.setUserMiddleName(meter.getBill().getResidence().getUser().getMiddleName());

        userBillDto.setOwnerName(meter.getBill().getResidence().getOwnerName());
        userBillDto.setOwnerSurname(meter.getBill().getResidence().getOwnerSurname());
        userBillDto.setCountry(meter.getBill().getResidence().getCountry());
        userBillDto.setCity(meter.getBill().getResidence().getCity());
        userBillDto.setStreet(meter.getBill().getResidence().getStreet());
        userBillDto.setHouse(meter.getBill().getResidence().getHouse());
        userBillDto.setCorps(meter.getBill().getResidence().getCorps());
        userBillDto.setFlat((meter.getBill().getResidence().getFlat()));

        userBillDto.setPersonalAccountNumber(meter.getBill().getPersonalAccountNumber());
        userBillDto.setCompanyName(meter.getBill().getCompany().getCompanyName());
        userBillDto.setIban(meter.getBill().getCompany().getIban());
        userBillDto.setUsreou(meter.getBill().getCompany().getUsreou());

        userBillDto.setPreliminaryIndicators(meter.getPreliminaryIndicators());
        userBillDto.setCurrentIndicators(meter.getCurrentIndicators());
        userBillDto.setIndicatorsDate(meter.getIndicatorsDate());

        userBillDto.setPrice(meter.getBill().getPrice());

        return userBillDto;
    }

}
