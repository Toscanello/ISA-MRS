package testing.service;

import app.domain.Discount;
import app.repository.DiscountRepository;
import app.service.DiscountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DiscountServiceClass {

    @Mock
    Discount discountMock;

    @Mock
    DiscountRepository discountRepositoryMock;

    @InjectMocks
    DiscountService discountService;

    @Test
    public void getCurrentDiscountTest() {
        Discount temp = new Discount();
        temp.setId(1L);
        temp.setStartDate(LocalDate.now().minusDays(5));
        temp.setEndDate(LocalDate.now().plusDays(5));

        when(discountRepositoryMock
                .getActiveDiscountByMedicineCodeAndPharmacyRegNo("lek1", "abc"))
        .thenReturn(temp);

        Discount disco = discountService.getCurrentDiscount("lek1", "abc");

        assertEquals((long) disco.getId(), 1L);

        verify(discountRepositoryMock, times(1))
                .getActiveDiscountByMedicineCodeAndPharmacyRegNo("lek", "abc");

    }

    @Test
    @Transactional
    @Rollback(true)
    public void testDiscountSave() {
        LocalDate now = LocalDate.now();
        Discount disc = new Discount();
        disc.setId(1L);
        disc.setStartDate(now.minusDays(5));
        disc.setEndDate(now.plusDays(5));

        Discount temp = new Discount();
        temp.setId(2L);
        temp.setStartDate(now.minusDays(5));
        temp.setEndDate(now.plusDays(5));

        when(discountRepositoryMock.findAll())
                .thenReturn(Collections.singletonList(temp));
        when(discountRepositoryMock.save(disc))
                .thenReturn(disc);

        int dbSizeBeforeSave = discountService.findAll().size();

        Discount savedDiscount = discountService.save(disc);

        when(discountRepositoryMock.findAll())
                .thenReturn(Arrays.asList(temp, disc));

        assertThat(savedDiscount, isNotNull());

        List<Discount> discounts = discountService.findAll();
        assertThat(discounts, hasSize(dbSizeBeforeSave+1));

        savedDiscount = discounts.get(discounts.size()-1);

        assertEquals((long) savedDiscount.getId(), 1L);

        verify(discountRepositoryMock, times(2)).findAll();
        verify(discountRepositoryMock, times(1)).save(disc);
    }
}
