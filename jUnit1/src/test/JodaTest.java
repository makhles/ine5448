package test;

import static org.junit.Assert.*;

import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;
import org.joda.time.IllegalFieldValueException;
import org.joda.time.Interval;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.junit.Test;

public class JodaTest {

	@Test
	public void criarData() {
		LocalDate quatorzeDeMarcoDe2017 = new LocalDate(2017, 3, 14);
		assertEquals(14, quatorzeDeMarcoDe2017.getDayOfMonth());
		assertEquals(DateTimeConstants.MARCH, quatorzeDeMarcoDe2017.getMonthOfYear());
		assertEquals(2017, quatorzeDeMarcoDe2017.getYear());
	}

	@Test(expected = IllegalFieldValueException.class)
	public void criarDataInvalida() {
		new LocalDate(2017, -3, 5);
	}
	
	@Test(expected = IllegalFieldValueException.class)
	public void criarDia29DeFevereiroEmAnoNaoBissexto() {
		new LocalDate(2001, 2, 29);
	}

	@Test
	public void criarMeioDia() {
		LocalTime meioDia = new LocalTime(12,0,0);
		assertEquals(12, meioDia.getHourOfDay());
		assertEquals(0, meioDia.getMinuteOfHour());
		assertEquals(0, meioDia.getSecondOfMinute());
	}

	@Test
	public void criarUltimoMinutoDoDia() {
		LocalTime ultimoSegundoDoDia = new LocalTime(23,59,59);
		assertEquals(23, ultimoSegundoDoDia.getHourOfDay());
		assertEquals(59, ultimoSegundoDoDia.getMinuteOfHour());
		assertEquals(59, ultimoSegundoDoDia.getSecondOfMinute());
	}

	@Test(expected = IllegalFieldValueException.class)
	public void criarHoraInvalida() {
		new LocalTime(-1,0,0);
	}

	@Test
	public void verificarMeioDiaEUmSegundo() {
		LocalTime meioDia = new LocalTime(12,0,0);
		LocalTime meioDiaEUmSegundo = new LocalTime(12,0,1);
		assertEquals(meioDiaEUmSegundo, meioDia.plusSeconds(1));
	}

	@Test
	public void verificarMeiaNoite() {
		LocalTime ultimoSegundoDoDia = new LocalTime(23,59,59);
		LocalTime meiaNoite = new LocalTime(0,0,0);
		assertEquals(meiaNoite, ultimoSegundoDoDia.plusSeconds(1));
	}

	@Test
	public void verificarSubtracaoDeSegundo() {
		LocalTime meiaNoite = new LocalTime(0,0,0);
		LocalTime ultimoSegundoDoDia = new LocalTime(23,59,59);
		assertEquals(ultimoSegundoDoDia, meiaNoite.minusSeconds(1));
	}
	
	public void verificarHoraAnterior() {
		LocalTime meiaNoite = new LocalTime(0,0,0);
		LocalTime ultimoSegundoDoDia = new LocalTime(23,59,59);
		assertTrue(ultimoSegundoDoDia.isBefore(meiaNoite));
	}
	
	public void verificarHoraPosterior() {
		LocalTime meiaNoite = new LocalTime(0,0,0);
		LocalTime ultimoSegundoDoDia = new LocalTime(23,59,59);
		assertTrue(meiaNoite.isAfter(ultimoSegundoDoDia));
	}

	@Test
	public void criarDataCompleta() {
		DateTime data = new DateTime(2017, 3, 14, 11, 20);
		assertEquals(2017, data.getYear());
		assertEquals(DateTimeConstants.MARCH, data.getMonthOfYear());
		assertEquals(14, data.getDayOfMonth());
		assertEquals(11, data.getHourOfDay());
		assertEquals(20, data.getMinuteOfHour());
	}
	
	public void verificarDataEntreIntervalo() {
		DateTime dataAnterior = new DateTime(2017, 3, 14, 11, 20);
		DateTime dataPosterior = new DateTime(2017, 3, 15, 11, 20);
		Interval intervalo = new Interval(dataAnterior, dataPosterior);
		DateTime dataNoMeio = new DateTime(2017, 3, 15, 11, 20);
		assertTrue(intervalo.contains(dataNoMeio));
		assertTrue(intervalo.contains(dataAnterior));
		assertTrue(intervalo.contains(dataPosterior));
	}
}
