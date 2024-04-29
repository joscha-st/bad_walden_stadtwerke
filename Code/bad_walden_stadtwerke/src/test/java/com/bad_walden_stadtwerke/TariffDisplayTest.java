package com.bad_walden_stadtwerke;

import com.bad_walden_stadtwerke.sales.types.Tariff;
import com.bad_walden_stadtwerke.ui.controller.initialSignUp.TariffDisplay;
import javafx.application.Platform;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TariffDisplayTest {
	static {
		Platform.startup(() -> {
		});
	}

	private ScrollPane scrollPane;
	private ArrayList<Tariff> tariffs;
	private VBox header;
	private TariffDisplay tariffDisplay;

	@BeforeEach
	public void setUp() {
		tariffs = new ArrayList<>();
		header = new VBox();
		tariffDisplay = Mockito.mock(TariffDisplay.class);
	}

	@Test
	public void whenAdding0Tariffs_then0RowsAreAdded() {
		addTariffsAndAssertRows(0);
	}

	@Test
	public void whenAdding1Tariffs_then1RowsAreAdded() {
		addTariffsAndAssertRows(1);
	}

	@Test
	public void whenAdding2Tariffs_then2RowsAreAdded() {
		addTariffsAndAssertRows(2);
	}

	@Test
	public void whenAdding3Tariffs_then3RowsAreAdded() {
		addTariffsAndAssertRows(3);
	}

	@Test
	public void whenAdding4Tariffs_then4RowsAreAdded() {
		addTariffsAndAssertRows(4);
	}

	@Test
	public void whenAdding5Tariffs_then5RowsAreAdded() {
		addTariffsAndAssertRows(5);
	}

	private void addTariffsAndAssertRows(int numberOfTariffs) {
		addTariffs(numberOfTariffs);
		assertRowsAdded(numberOfTariffs);
	}

	private void addTariffs(int numberOfTariffs) {
		for (int i = 0; i < numberOfTariffs; i++) {
			Tariff tariff = new Tariff(i, "Test" + i, "Test Description" + i, 100 * i, "€", 12 * i, i, "electricity");
			tariffs.add(tariff);
		}

		scrollPane = new ScrollPane();
		tariffDisplay = new TariffDisplay(scrollPane, tariffs, header);
	}

	private void assertRowsAdded(int expectedRows) {
		VBox content = (VBox) scrollPane.getContent();
		int totalChildren = content.getChildren().size();
		assertEquals(expectedRows, totalChildren);
	}
}