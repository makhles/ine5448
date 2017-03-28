package test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.number.OrderingComparison.*;
import static org.hamcrest.collection.IsArrayContainingInOrder.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

//import org.hamcrest.collection.IsArrayContainingInOrder;
import org.junit.Test;

public class TesteComHamcrest {

	// OBJECT MATCHER
	@Test
	public void testObject() throws Exception {
		assertThat("Blah", is(equalTo("Blah")));
	}

	// ARRAY MATCHER
	@Test
	public void testArray() throws Exception {
		Integer[] numeros = {1, 2, 3};
		assertThat(numeros, arrayContaining(1,2,3));
	}

	@Test
	public void verificarItemPresente() throws Exception {
		List<String> cidades = Arrays.asList("Joinville", "Floripa", "Criciúma");
		assertThat(cidades, hasItem("Joinville"));
	}

	// NUMBER MATCHER
	@Test
	public void testNumber() throws Exception {
		assertThat(5, allOf(greaterThan(2), lessThanOrEqualTo(6)));
	}

	// TEXT MATCHER
	@Test
	public void testText() throws Exception {
		String telefone = "+55 48 99999-9999";
		assertThat(telefone, startsWith("+55"));
	}
}
