package com.canoo.codecamp.dolphinpi

import javafx.beans.property.SimpleStringProperty
import javafx.beans.value.ObservableValue
import javafx.collections.ObservableList
import javafx.event.EventHandler
import javafx.scene.control.TableColumn
import javafx.scene.control.TableColumnBuilder
import javafx.scene.control.TableView
import javafx.scene.control.TableViewBuilder
import javafx.scene.control.cell.TextFieldTableCell
import javafx.util.Callback
import org.opendolphin.core.client.ClientAttributeWrapper
import org.opendolphin.core.client.ClientPresentationModel

import static com.canoo.codecamp.dolphinpi.ApplicationConstants.*
import static org.opendolphin.binding.JavaFxUtil.cellEdit

class MasterViewFactory {

	static javafx.scene.Node newMasterView(ObservableList<ClientPresentationModel> data){

		TableView result = TableViewBuilder.create()
			.items(data)
			.columns(
				newTableColumn(ATT_DEPARTURE_TIME, "Uhrzeit"),
				newTableColumn(ATT_TRAIN_NUMBER, "Fahrt"),
				newTableColumn(ATT_DESTINATION, "Richtung"),
				newTableColumn(ATT_STATUS, "Status"),
				newTableColumn(ATT_TRACK, "Gleis"),
			)
		.columnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY).build()
		result.setEditable(true)
		return result

	}

	static newTableColumn(String inPropertyName, String inTitle) {
		TableColumnBuilder.create()
			.text(inTitle)
			.cellFactory(TextFieldTableCell.forTableColumn())
			.cellValueFactory({ row -> new ClientAttributeWrapper(row.value[inPropertyName]) } as Callback)
			.onEditCommit(cellEdit(inPropertyName, { it }) as EventHandler)
			.editable(true)
			.build()
	}

	public static Callback newCallback(String inPropertyName) {
		def result = new Callback<TableColumn.CellDataFeatures<ClientPresentationModel, String>, ObservableValue<String>>() {

			@Override
			public ObservableValue<String> call(TableColumn.CellDataFeatures<ClientPresentationModel, String> cellDataFeatures) {
				ClientPresentationModel pm = cellDataFeatures.getValue()
				if (pm != null) {
					return new SimpleStringProperty(pm.findAttributeByPropertyName(inPropertyName).value as String);
				} else {
					return new SimpleStringProperty("...");
				}
			}
		}
		result
	}

}