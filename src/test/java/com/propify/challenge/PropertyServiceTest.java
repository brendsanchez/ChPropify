package com.propify.challenge;

import com.propify.challenge.exception.NotValidException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class PropertyServiceTest {
    private Set<Property> properties;
    private Property property;

    @InjectMocks
    private PropertyService propertyService;
    @Mock
    private PropertyMapper propertyMapper;
    @Mock
    private AddressMapper addressMapper;
    @Mock
    private AlertService alertService;

    // TODO: add at least 3 tests to the method propertyService.propertyReport()
    @BeforeEach
    void init() {
        Property propertyIl1 = Property.builder()
                .rentPrice(29.99)
                .address(Address.builder().state("IL").build())
                .type(PropertyType.CONDOMINIUM)
                .build();

        Property propertyIl2 = Property.builder()
                .rentPrice(52.00)
                .address(Address.builder().state("IL").build())
                .type(PropertyType.SINGLE_FAMILY)
                .build();

        Property propertyDC = Property.builder()
                .rentPrice(24.50)
                .address(Address.builder().state("DC").build())
                .type(PropertyType.SINGLE_FAMILY)
                .build();


        properties = new HashSet<>() {{
            add(propertyIl1);
            add(propertyIl2);
            add(propertyDC);
        }};
    }

    @Test
    @DisplayName("when price valid")
    void testPriceValid() throws NotValidException {
        property = Property.builder()
                .rentPrice(29.99)
                .build();
        propertyService.insert(property);
        Mockito.verify(propertyMapper).insert(property);
    }

    @Test
    @DisplayName("when price is > 2 decimals")
    void testPriceNotValidDecimals() {
        property = Property.builder()
                .rentPrice(29.559)
                .build();
        assertThrows(NotValidException.class, () -> propertyService.insert(property));
    }

    @Test
    void testPropertyReport() {
        Map<PropertyType, Integer> result = Map.ofEntries(
                Map.entry(PropertyType.SINGLE_FAMILY, 2),
                Map.entry(PropertyType.CONDOMINIUM, 1),
                Map.entry(PropertyType.MULTI_FAMILY, 0),
                Map.entry(PropertyType.TOWNHOUSE, 0)
        );

        Mockito.when(propertyMapper.search(Mockito.any(), Mockito.any())).thenReturn(properties);

        PropertyReport serviceMock = propertyService.propertyReport();

        assertEquals(result, serviceMock.getQuantityPerType());
        assertEquals(35.50, serviceMock.getAverageRentPrice());
        assertEquals(2, serviceMock.getIllinoisQuantity());
        assertEquals(3, serviceMock.getTotalQuantity());
    }
}
