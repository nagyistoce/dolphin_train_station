package com.canoo.codecamp.dolphinpi

import groovyx.gpars.dataflow.DataflowQueue;
import org.opendolphin.core.comm.Command;
import org.opendolphin.core.server.DTO;
import org.opendolphin.core.server.EventBus;
import org.opendolphin.core.server.Slot;
import org.opendolphin.core.server.action.DolphinServerAction;
import org.opendolphin.core.server.comm.ActionRegistry;
import org.opendolphin.core.server.comm.CommandHandler

import static com.canoo.codecamp.dolphinpi.ApplicationConstants.*;

class ApplicationRegistrationAction extends DolphinServerAction {

	private static EventBus eventBus = new EventBus();
	private final DataflowQueue valueQueue = new DataflowQueue();

	private final int EVENT_PROVIDER_WAIT_MS = 500;
	private final int EVENT_CONSUMER_WAIT_MS = 5000;
	private int waitMillis = EVENT_CONSUMER_WAIT_MS;


	public void registerIn(ActionRegistry actionRegistry) {

		actionRegistry.register(COMMAND_GET_ALL_DEPARTURES, new CommandHandler<Command>() {
			public void handleCommand(Command command, List<Command> response) {
					List<DTO> dtos = []
					populateDTOs(dtos)

					dtos.eachWithIndex { dto, index ->
					presentationModel pmId(TYPE_DEPARTURE, index), TYPE_DEPARTURE, dto
				}

			}
		});

	}

	DTO createDeparture(departureTime, trainNumber, destination, stopOvers, track) {
		new DTO(Slot.slots([(ATT_DEPARTURE_TIME): departureTime, (ATT_TRAIN_NUMBER): trainNumber, (ATT_DESTINATION): destination, (ATT_TRACK): track, (ATT_STATUS): "unbekannt"]))
	}

	def populateDTOs(dtos) {
		dtos.add(createDeparture("00:00", "IC 747", "Zürich HB", "Olten  00:00 - Aarau  00:08 - Zürich HB  00:33", ""));
		dtos.add(createDeparture("00:04", "IC 746", "Bern", "Olten  00:04 - Bern  00:31", ""));
		dtos.add(createDeparture("00:33", "EC 158", "Basel SBB", "Olten  00:33 - Liestal  00:48 - Basel SBB  00:59", "10"));
		dtos.add(createDeparture("00:33", "IC 849", "Zürich HB", "Olten  00:33 - Zürich HB  01:04", ""));
		dtos.add(createDeparture("00:35", "IC 800", "Bern", "Olten  00:35 - Bern  01:02", ""));
		dtos.add(createDeparture("00:35", "ICN 1549", "Zürich HB", "Olten  00:35 - Aarau  00:43 - Lenzburg  00:51 - Zürich HB  01:12", "2"));
		dtos.add(createDeparture("00:37", "IC 1096", "Basel SBB", "Olten  00:37 - Liestal  00:52 - Basel SBB  01:03", ""));
		dtos.add(createDeparture("05:30", "ICN 653", "Lugano", "Olten  05:30 - Luzern  06:05 - Arth-Goldau  06:44 - Bellinzona  08:23 - Lugano  08:46", "12"));
		dtos.add(createDeparture("05:57", "IC 707", "St. Gallen", "Olten  05:57 - Zürich HB  06:28 - Zürich Flughafen  06:50 - Winterthur  07:05 - Wil SG  07:24 - Uzwil  07:32 - Flawil  07:38 - Gossau SG  07:43 - St. Gallen  07:53", ""));
		dtos.add(createDeparture("05:59", "ICE 1055", "Interlaken Ost", "Olten  05:59 - Bern  06:27 - Thun  06:52 - Spiez  07:02 - Interlaken West  07:22 - Interlaken Ost  07:28", "12"));
		dtos.add(createDeparture("06:03", "IC 706", "Genf-Flughafen", "Olten  06:03 - Bern  06:28 - Fribourg  06:55 - Lausanne  07:40 - Genf  08:15 - Genf-Flughafen  08:24", "8"));
		dtos.add(createDeparture("06:20", "ICN 509", "St. Gallen", "Olten  06:20 - Aarau  06:28 - Zürich HB  06:56 - Zürich Flughafen  07:20 - Winterthur  07:35 - Wil SG  07:52 - Gossau SG  08:06 - St. Gallen  08:15", "7"));
		dtos.add(createDeparture("06:29", "IC 957", "Interlaken Ost", "Olten  06:29 - Bern  06:56 - Thun  07:21 - Spiez  07:31 - Interlaken West  07:51 - Interlaken Ost  07:57", "11"));
		dtos.add(createDeparture("06:31", "IC 809", "Romanshorn", "Olten  06:31 - Zürich HB  07:02 - Zürich Flughafen  07:16 - Winterthur  07:33 - Frauenfeld  07:46 - Weinfelden  07:58 - Amriswil  08:10 - Romanshorn  08:18", "7"));
		dtos.add(createDeparture("06:32", "IC 952", "Basel SBB", "Olten  06:32 - Liestal  06:47 - Basel SBB  06:59", "3"));
		dtos.add(createDeparture("06:40", "ICN 508", "Genf-Flughafen", "Olten  06:40 - Solothurn  06:56 - Biel/Bienne  07:13 - Neuchâtel  07:35 - Yverdon-les-Bains  07:54 - Morges  08:18 - Nyon  08:32 - Genf  08:46 - Genf-Flughafen  08:56", "8"));
		dtos.add(createDeparture("06:59", "EC 51", "Iselle di Trasquera", "Olten  06:59 - Bern  07:27 - Thun  07:52 - Spiez  08:02 - Visp  08:31 - Brig  08:40 - Iselle di Trasquera  08:58", ""));
		dtos.add(createDeparture("07:05", "IC 1058", "Basel SBB", "Olten  07:05 - Basel SBB  07:29", "3"));
		dtos.add(createDeparture("07:20", "ICN 1511", "St. Gallen", "Olten  07:20 - Aarau  07:28 - Zürich HB  07:56 - Zürich Flughafen  08:20 - Winterthur  08:35 - Wil SG  08:52 - Gossau SG  09:06 - St. Gallen  09:15", "7"));
		dtos.add(createDeparture("07:29", "IC 959", "Interlaken Ost", "Olten  07:29 - Bern  07:56 - Thun  08:21 - Spiez  08:31 - Interlaken West  08:51 - Interlaken Ost  08:57", "11"));
		dtos.add(createDeparture("07:30", "EC 153", "Milano Centrale", "Olten  07:30 - Luzern  08:05 - Arth-Goldau  08:44 - Bellinzona  10:23 - Lugano  10:46 - Chiasso  11:08 - Como S. Giovanni  11:15 - Milano Centrale  11:50", "12"));
		dtos.add(createDeparture("07:32", "ICE 1178", "Berlin Hbf (Tief)", "Olten  07:32 - Liestal  07:47 - Basel SBB  07:59 - Basel Bad Bf  08:18 • Karlsruhe Hbf  09:58 - Mannheim Hbf  10:22 - Frankfurt (Main) Hbf  11:08 - Kassel-Wilhelmshöhe  12:41 - Berlin-Spandau  15:11 - Berlin Hbf (Tief)  15:22", "7"));
		dtos.add(createDeparture("07:40", "ICN 1510", "Lausanne", "Olten  07:40 - Solothurn  07:56 - Biel/Bienne  08:13 - Neuchâtel  08:32 - Yverdon-les-Bains  08:51 - Lausanne  09:15", "8"));
		dtos.add(createDeparture("07:59", "ICE 1061", "Interlaken Ost", "Olten  07:59 - Bern  08:27 - Thun  08:52 - Spiez  09:02 - Interlaken West  09:22 - Interlaken Ost  09:28", "12"));
		dtos.add(createDeparture("08:05", "IC 1060", "Basel SBB", "Olten  08:05 - Basel SBB  08:29", "3"));
		dtos.add(createDeparture("08:20", "ICN 515", "St. Gallen", "Olten  08:20 - Aarau  08:28 - Zürich HB  08:56 - Zürich Flughafen  09:20 - Winterthur  09:35 - Wil SG  09:52 - Gossau SG  10:06 - St. Gallen  10:15", "7"));
		dtos.add(createDeparture("08:29", "IC 961", "Interlaken Ost", "Olten  08:29 - Bern  08:56 - Thun  09:21 - Spiez  09:31 - Interlaken West  09:51 - Interlaken Ost  09:57", ""));
		dtos.add(createDeparture("08:32", "IC 956", "Basel SBB", "Olten  08:32 - Liestal  08:47 - Basel SBB  08:59", "7"));
		dtos.add(createDeparture("08:40", "ICN 512", "Genf-Flughafen", "Olten  08:40 - Solothurn  08:56 - Biel/Bienne  09:13 - Neuchâtel  09:35 - Yverdon-les-Bains  09:54 - Morges  10:18 - Nyon  10:32 - Genf  10:46 - Genf-Flughafen  10:56", "8"));
		dtos.add(createDeparture("08:59", "IC 1063", "Brig", "Olten  08:59 - Bern  09:27 - Thun  09:52 - Spiez  10:02 - Visp  10:31 - Brig  10:40", "12"));
		dtos.add(createDeparture("09:05", "IC 1064", "Basel SBB", "Olten  09:05 - Basel SBB  09:29", "3"));
		dtos.add(createDeparture("09:20", "ICN 1517", "St. Gallen", "Olten  09:20 - Aarau  09:28 - Zürich HB  09:56 - Zürich Flughafen  10:20 - Winterthur  10:35 - Wil SG  10:52 - Gossau SG  11:06 - St. Gallen  11:15", "7"));
		dtos.add(createDeparture("09:29", "ICE 5", "Interlaken Ost", "Olten  09:29 - Bern  09:56 - Thun  10:21 - Spiez  10:31 - Interlaken West  10:51 - Interlaken Ost  10:57", ""));
		dtos.add(createDeparture("09:30", "ICN 669", "Lugano", "Olten  09:30 - Luzern  10:05 - Arth-Goldau  10:44 - Bellinzona  12:23 - Lugano  12:46", ""));
		dtos.add(createDeparture("09:32", "ICE 276", "Berlin-Ostbahnhof", "Olten  09:32 - Liestal  09:47 - Basel SBB  09:59 - Basel Bad Bf  10:18 • Karlsruhe Hbf  11:58 - Mannheim Hbf  12:23 - Frankfurt (Main) Hbf  13:08 - Kassel-Wilhelmshöhe  14:41 - Berlin-Spandau  17:11 - Berlin-Ostbahnhof  17:37", "7"));
		dtos.add(createDeparture("09:40", "ICN 1516", "Lausanne", "Olten  09:40 - Solothurn  09:56 - Biel/Bienne  10:13 - Neuchâtel  10:32 - Yverdon-les-Bains  10:51 - Lausanne  11:15", "8"));
		dtos.add(createDeparture("09:59", "IC 1067", "Interlaken Ost", "Olten  09:59 - Bern  10:27 - Thun  10:52 - Spiez  11:02 - Interlaken West  11:22 - Interlaken Ost  11:28", "12"));
		dtos.add(createDeparture("10:05", "IC 1066", "Basel SBB", "Olten  10:05 - Basel SBB  10:29", "3"));
		dtos.add(createDeparture("10:20", "ICN 519", "St. Gallen", "Olten  10:20 - Aarau  10:28 - Zürich HB  10:56 - Zürich Flughafen  11:20 - Winterthur  11:35 - Wil SG  11:52 - Gossau SG  12:06 - St. Gallen  12:15", "7"));
		dtos.add(createDeparture("10:29", "IC 967", "Interlaken Ost", "Olten  10:29 - Bern  10:56 - Thun  11:21 - Spiez  11:31 - Interlaken West  11:51 - Interlaken Ost  11:57", ""));
		dtos.add(createDeparture("10:30", "ICN 658", "Basel SBB", "Olten  10:30 - Basel SBB  10:55", ""));
		dtos.add(createDeparture("10:32", "IC 962", "Basel SBB", "Olten  10:32 - Liestal  10:47 - Basel SBB  10:59", "7"));
		dtos.add(createDeparture("10:40", "ICN 518", "Genf-Flughafen", "Olten  10:40 - Solothurn  10:56 - Biel/Bienne  11:13 - Neuchâtel  11:35 - Yverdon-les-Bains  11:54 - Morges  12:18 - Nyon  12:32 - Genf  12:46 - Genf-Flughafen  12:56", "8"));
		dtos.add(createDeparture("10:59", "IC 1069", "Brig", "Olten  10:59 - Bern  11:27 - Thun  11:52 - Spiez  12:02 - Visp  12:31 - Brig  12:40", ""));
		dtos.add(createDeparture("11:20", "ICN 1521", "St. Gallen", "Olten  11:20 - Aarau  11:28 - Zürich HB  11:56 - Zürich Flughafen  12:20 - Winterthur  12:35 - Wil SG  12:52 - Gossau SG  13:06 - St. Gallen  13:15", "7"));
		dtos.add(createDeparture("11:29", "IC 969", "Interlaken Ost", "Olten  11:29 - Bern  11:56 - Thun  12:21 - Spiez  12:31 - Interlaken West  12:51 - Interlaken Ost  12:57", "11"));
		dtos.add(createDeparture("11:30", "ICN 675", "Lugano", "Olten  11:30 - Luzern  12:05 - Arth-Goldau  12:44 - Bellinzona  14:23 - Lugano  14:46", "12"));
		dtos.add(createDeparture("11:32", "ICE 374", "Berlin-Ostbahnhof", "Olten  11:32 - Liestal  11:47 - Basel SBB  11:59 - Basel Bad Bf  12:18 • Karlsruhe Hbf  13:58 - Mannheim Hbf  14:22 - Frankfurt (Main) Hbf  15:08 - Kassel-Wilhelmshöhe  16:41 - Berlin-Spandau  19:11 - Berlin-Ostbahnhof  19:37", "7"));
		dtos.add(createDeparture("11:40", "ICN 1520", "Lausanne", "Olten  11:40 - Solothurn  11:56 - Biel/Bienne  12:13 - Neuchâtel  12:32 - Yverdon-les-Bains  12:51 - Lausanne  13:15", "8"));
		dtos.add(createDeparture("11:59", "IC 1071", "Interlaken Ost", "Olten  11:59 - Bern  12:27 - Thun  12:52 - Spiez  13:02 - Interlaken West  13:22 - Interlaken Ost  13:28", "12"));
		dtos.add(createDeparture("12:05", "IC 1070", "Basel SBB", "Olten  12:05 - Basel SBB  12:29", "3"));
		dtos.add(createDeparture("12:20", "ICN 523", "St. Gallen", "Olten  12:20 - Aarau  12:28 - Zürich HB  12:56 - Zürich Flughafen  13:20 - Winterthur  13:35 - Wil SG  13:52 - Gossau SG  14:06 - St. Gallen  14:15", "7"));
		dtos.add(createDeparture("12:29", "ICE 373", "Interlaken Ost", "Olten  12:29 - Bern  12:56 - Thun  13:21 - Spiez  13:31 - Interlaken West  13:51 - Interlaken Ost  13:57", ""));
		dtos.add(createDeparture("12:30", "ICN 662", "Basel SBB", "Olten  12:30 - Basel SBB  12:55", ""));
		dtos.add(createDeparture("12:32", "IC 968", "Basel SBB", "Olten  12:32 - Liestal  12:47 - Basel SBB  12:59", "7"));
		dtos.add(createDeparture("12:40", "ICN 522", "Genf-Flughafen", "Olten  12:40 - Solothurn  12:56 - Biel/Bienne  13:13 - Neuchâtel  13:35 - Yverdon-les-Bains  13:54 - Morges  14:18 - Nyon  14:32 - Genf  14:46 - Genf-Flughafen  14:56", "8"));
		dtos.add(createDeparture("12:59", "EC 57", "Iselle di Trasquera", "Olten  12:59 - Bern  13:27 - Thun  13:52 - Spiez  14:02 - Visp  14:31 - Brig  14:40 - Iselle di Trasquera  14:58", ""));
		dtos.add(createDeparture("13:05", "IC 1072", "Basel SBB", "Olten  13:05 - Basel SBB  13:29", "3"));
		dtos.add(createDeparture("13:20", "ICN 1525", "St. Gallen", "Olten  13:20 - Aarau  13:28 - Zürich HB  13:56 - Zürich Flughafen  14:20 - Winterthur  14:35 - Wil SG  14:52 - Gossau SG  15:06 - St. Gallen  15:15", "7"));
		dtos.add(createDeparture("13:29", "IC 973", "Interlaken Ost", "Olten  13:29 - Bern  13:56 - Thun  14:21 - Spiez  14:31 - Interlaken West  14:51 - Interlaken Ost  14:57", ""));
		dtos.add(createDeparture("13:30", "ICN 679", "Lugano", "Olten  13:30 - Luzern  14:05 - Arth-Goldau  14:44 - Bellinzona  16:23 - Lugano  16:46", "12"));
		dtos.add(createDeparture("13:32", "ICE 370", "Berlin-Ostbahnhof", "Olten  13:32 - Liestal  13:47 - Basel SBB  13:59 - Basel Bad Bf  14:18 • Karlsruhe Hbf  15:58 - Mannheim Hbf  16:22 - Frankfurt (Main) Hbf  17:08 - Kassel-Wilhelmshöhe  18:41 - Berlin-Spandau  21:11 - Berlin-Ostbahnhof  21:37", "7"));
		dtos.add(createDeparture("13:40", "ICN 1524", "Lausanne", "Olten  13:40 - Solothurn  13:56 - Biel/Bienne  14:13 - Neuchâtel  14:32 - Yverdon-les-Bains  14:51 - Lausanne  15:15", "8"));
		dtos.add(createDeparture("13:59", "IC 1075", "Interlaken Ost", "Olten  13:59 - Bern  14:27 - Thun  14:52 - Spiez  15:02 - Interlaken West  15:22 - Interlaken Ost  15:28", "12"));
		dtos.add(createDeparture("14:05", "IC 1074", "Basel SBB", "Olten  14:05 - Basel SBB  14:29", "3"));
		dtos.add(createDeparture("14:20", "ICN 527", "St. Gallen", "Olten  14:20 - Aarau  14:28 - Zürich HB  14:56 - Zürich Flughafen  15:20 - Winterthur  15:35 - Wil SG  15:52 - Gossau SG  16:06 - St. Gallen  16:15", "7"));
		dtos.add(createDeparture("14:29", "ICE 291", "Interlaken Ost", "Olten  14:29 - Bern  14:56 - Thun  15:21 - Spiez  15:31 - Interlaken West  15:51 - Interlaken Ost  15:57", ""));
		dtos.add(createDeparture("14:30", "ICN 666", "Basel SBB", "Olten  14:30 - Basel SBB  14:55", ""));
		dtos.add(createDeparture("14:32", "IC 974", "Basel SBB", "Olten  14:32 - Liestal  14:47 - Basel SBB  14:59", "7"));
		dtos.add(createDeparture("14:40", "ICN 526", "Genf-Flughafen", "Olten  14:40 - Solothurn  14:56 - Biel/Bienne  15:13 - Neuchâtel  15:35 - Yverdon-les-Bains  15:54 - Morges  16:18 - Nyon  16:32 - Genf  16:46 - Genf-Flughafen  16:56", "8"));
		dtos.add(createDeparture("14:59", "IC 1077", "Brig", "Olten  14:59 - Bern  15:27 - Thun  15:52 - Spiez  16:02 - Visp  16:31 - Brig  16:40", "12"));
		dtos.add(createDeparture("15:20", "ICN 1529", "St. Gallen", "Olten  15:20 - Aarau  15:28 - Zürich HB  15:56 - Zürich Flughafen  16:20 - Winterthur  16:35 - Wil SG  16:52 - Gossau SG  17:06 - St. Gallen  17:15", "7"));
		dtos.add(createDeparture("15:29", "IC 977", "Interlaken Ost", "Olten  15:29 - Bern  15:56 - Thun  16:21 - Spiez  16:31 - Interlaken West  16:51 - Interlaken Ost  16:57", "11"));
		dtos.add(createDeparture("15:30", "ICN 683", "Lugano", "Olten  15:30 - Luzern  16:05 - Arth-Goldau  16:44 - Bellinzona  18:23 - Lugano  18:46", "12"));
		dtos.add(createDeparture("15:32", "IC 978", "Basel SBB", "Olten  15:32 - Liestal  15:47 - Basel SBB  15:59", "7"));
		dtos.add(createDeparture("15:40", "ICN 1528", "Lausanne", "Olten  15:40 - Solothurn  15:56 - Biel/Bienne  16:13 - Neuchâtel  16:32 - Yverdon-les-Bains  16:51 - Lausanne  17:15", "8"));
		dtos.add(createDeparture("15:59", "IC 1079", "Interlaken Ost", "Olten  15:59 - Bern  16:27 - Thun  16:52 - Spiez  17:02 - Interlaken West  17:22 - Interlaken Ost  17:28", "12"));
		dtos.add(createDeparture("16:05", "IC 1078", "Basel SBB", "Olten  16:05 - Basel SBB  16:29", "3"));
		dtos.add(createDeparture("16:20", "ICN 531", "St. Gallen", "Olten  16:20 - Aarau  16:28 - Zürich HB  16:56 - Zürich Flughafen  17:20 - Winterthur  17:35 - Wil SG  17:52 - Gossau SG  18:06 - St. Gallen  18:15", "7"));
		dtos.add(createDeparture("16:29", "IC 979", "Interlaken Ost", "Olten  16:29 - Bern  16:56 - Thun  17:21 - Spiez  17:31 - Interlaken West  17:51 - Interlaken Ost  17:57", ""));
		dtos.add(createDeparture("16:30", "ICN 670", "Basel SBB", "Olten  16:30 - Basel SBB  16:55", ""));
		dtos.add(createDeparture("16:32", "ICE 376", "Hamburg-Altona", "Olten  16:32 - Liestal  16:47 - Basel SBB  16:59 - Basel Bad Bf  17:10 • Karlsruhe Hbf  18:49 - Mannheim Hbf  19:14 - Frankfurt (Main) Hbf  19:53 - Hannover Hbf  22:17 - Hamburg Hbf  23:51 - Hamburg-Altona  00:06", "7"));
		dtos.add(createDeparture("16:40", "ICN 530", "Genf-Flughafen", "Olten  16:40 - Solothurn  16:56 - Biel/Bienne  17:13 - Neuchâtel  17:35 - Yverdon-les-Bains  17:54 - Morges  18:18 - Nyon  18:32 - Genf  18:46 - Genf-Flughafen  18:56", "8"));
		dtos.add(createDeparture("16:59", "IC 1081", "Brig", "Olten  16:59 - Bern  17:27 - Thun  17:52 - Spiez  18:02 - Visp  18:31 - Brig  18:40", "12"));
		dtos.add(createDeparture("17:05", "IC 1080", "Basel SBB", "Olten  17:05 - Basel SBB  17:29", ""));
		dtos.add(createDeparture("17:20", "ICN 1535", "St. Gallen", "Olten  17:20 - Aarau  17:28 - Zürich HB  17:56 - Zürich Flughafen  18:20 - Winterthur  18:35 - Wil SG  18:52 - Gossau SG  19:06 - St. Gallen  19:15", "7"));
		dtos.add(createDeparture("17:29", "IC 981", "Interlaken Ost", "Olten  17:29 - Bern  17:56 - Thun  18:21 - Spiez  18:31 - Interlaken West  18:51 - Interlaken Ost  18:57", "11"));
		dtos.add(createDeparture("17:30", "ICN 687", "Chiasso", "Olten  17:30 - Luzern  18:05 - Arth-Goldau  18:44 - Bellinzona  20:23 - Lugano  20:46 - Chiasso  21:10", "12"));
		dtos.add(createDeparture("17:32", "IC 982", "Basel SBB", "Olten  17:32 - Liestal  17:47 - Basel SBB  17:59", "7"));
		dtos.add(createDeparture("17:40", "ICN 1532", "Lausanne", "Olten  17:40 - Solothurn  17:56 - Biel/Bienne  18:13 - Neuchâtel  18:32 - Yverdon-les-Bains  18:51 - Lausanne  19:15", "8"));
		dtos.add(createDeparture("17:59", "EC 59", "Bern", "Olten  17:59 - Bern  18:27", "12"));
		dtos.add(createDeparture("17:59", "EC 59", "Iselle di Trasquera", "Olten  17:59 - Bern  18:27 - Thun  18:52 - Spiez  19:02 - Visp  19:31 - Brig  19:40 - Iselle di Trasquera  19:58", "12"));
		dtos.add(createDeparture("18:20", "ICN 537", "St. Gallen", "Olten  18:20 - Aarau  18:28 - Zürich HB  18:56 - Zürich Flughafen  19:20 - Winterthur  19:35 - Wil SG  19:52 - Gossau SG  20:06 - St. Gallen  20:15", "7"));
		dtos.add(createDeparture("18:05", "ICE 1082", "Basel SBB", "Olten  18:05 - Basel SBB  18:29", "3"));
		dtos.add(createDeparture("18:29", "ICE 277", "Interlaken Ost", "Olten  18:29 - Bern  18:56 - Thun  19:21 - Spiez  19:31 - Interlaken West  19:51 - Interlaken Ost  19:57", "11"));
		dtos.add(createDeparture("18:30", "ICN 674", "Basel SBB", "Olten  18:30 - Basel SBB  18:55", ""));
		dtos.add(createDeparture("18:32", "IC 986", "Basel SBB", "Olten  18:32 - Liestal  18:47 - Basel SBB  18:59", "7"));
		dtos.add(createDeparture("18:40", "ICN 536", "Genf-Flughafen", "Olten  18:40 - Solothurn  18:56 - Biel/Bienne  19:13 - Neuchâtel  19:35 - Yverdon-les-Bains  19:54 - Morges  20:18 - Nyon  20:32 - Genf  20:46 - Genf-Flughafen  20:56", "8"));
		dtos.add(createDeparture("18:59", "IC 1085", "Interlaken Ost,", "Olten  18:59 - Bern  19:27 - Thun  19:52 - Spiez  20:02 - Interlaken West  20:22 - Interlaken Ost  20:28", "12"));
		dtos.add(createDeparture("19:05", "IC 1086", "Basel SBB", "Olten  19:05 - Basel SBB  19:29", "3"));
		dtos.add(createDeparture("19:20", "ICN 1539", "St. Gallen", "Olten  19:20 - Aarau  19:28 - Zürich HB  19:56 - Zürich Flughafen  20:20 - Winterthur  20:35 - Wil SG  20:52 - Gossau SG  21:06 - St. Gallen  21:15", "7"));
		dtos.add(createDeparture("19:29", "ICE 987", "Interlaken Ost", "Olten  19:29 - Bern  19:56 - Thun  20:21 - Spiez  20:31 - Interlaken West  20:48 - Interlaken Ost  20:54", "11"));
		dtos.add(createDeparture("19:30", "ICN 691", "Chiasso", "Olten  19:30 - Luzern  20:05 - Arth-Goldau  20:44 - Bellinzona  22:23 - Lugano  22:46 - Mendrisio  23:03 - Chiasso  23:12", "12"));
		dtos.add(createDeparture("19:32", "IC 988", "Basel SBB", "Olten  19:32 - Liestal  19:47 - Basel SBB  19:59", "7"));
		dtos.add(createDeparture("19:40", "ICN 1538", "Lausanne", "Olten  19:40 - Solothurn  19:56 - Biel/Bienne  20:13 - Neuchâtel  20:32 - Yverdon-les-Bains  20:51 - Lausanne  21:15", "8"));
		dtos.add(createDeparture("19:59", "IC 1087", "Brig", "Olten  19:59 - Bern  20:27 - Thun  20:52 - Spiez  21:02 - Visp  21:31 - Brig  21:40", "12"));
		dtos.add(createDeparture("20:05", "IC 1088", "Basel SBB", "Olten  20:05 - Basel SBB  20:29", "3"));
		dtos.add(createDeparture("20:20", "ICN 541", "St. Gallen", "Olten  20:20 - Aarau  20:28 - Zürich HB  20:56 - Zürich Flughafen  21:20 - Winterthur  21:35 - Wil SG  21:52 - Gossau SG  22:06 - St. Gallen  22:15", "7"));
		dtos.add(createDeparture("20:29", "ICE 279", "Interlaken Ost", "Olten  20:29 - Bern  20:56 - Thun  21:24 - Spiez  21:34 - Interlaken West  21:51 - Interlaken Ost  21:56", "11"));
		dtos.add(createDeparture("20:30", "ICN 678", "Basel SBB", "Olten  20:30 - Basel SBB  20:55", ""));
		dtos.add(createDeparture("20:32", "IC 990", "Basel SBB", "Olten  20:32 - Liestal  20:47 - Basel SBB  20:59", "7"));
		dtos.add(createDeparture("20:40", "ICN 1540", "Biel/Bienne", "Olten  20:40 - Solothurn  20:56 - Biel/Bienne  21:13", "8"));
		dtos.add(createDeparture("20:59", "IC 1089", "Brig", "Olten  20:59 - Bern  21:27 - Thun  21:52 - Spiez  22:02 - Visp  22:31 - Brig  22:40", "12"));
		dtos.add(createDeparture("21:05", "IC 1090", "Basel SBB", "Olten  21:05 - Basel SBB  21:29", "3"));
		dtos.add(createDeparture("21:20", "ICN 1543", "St. Gallen", "Olten  21:20 - Aarau  21:28 - Zürich HB  21:56 - Zürich Flughafen  22:20 - Winterthur  22:35 - Wil SG  22:52 - Uzwil  23:00 - Gossau SG  23:09 - St. Gallen  23:17", "7"));
		dtos.add(createDeparture("21:29", "IC 991", "Interlaken Ost", "Olten  21:29 - Bern  21:56 - Thun  22:24 - Spiez  22:34 - Interlaken West  22:51 - Interlaken Ost  22:57", "11"));
		dtos.add(createDeparture("21:32", "IC 992", "Basel SBB", "Olten  21:32 - Liestal  21:47 - Basel SBB  21:59", "7"));
		dtos.add(createDeparture("21:40", "ICN 1542", "Lausanne", "Olten  21:40 - Solothurn  21:56 - Biel/Bienne  22:13 - Neuchâtel  22:32 - Yverdon-les-Bains  22:51 - Lausanne  23:15", "8"));
		dtos.add(createDeparture("22:00", "IC 743", "Zürich HB", "Olten  22:00 - Zürich HB  22:31", "7"));
		dtos.add(createDeparture("22:00", "IC 1093", "Bern", "Olten  22:00 - Bern  22:27", "12"));
		dtos.add(createDeparture("22:20", "ICN 545", "St. Gallen", "Olten  22:20 - Aarau  22:29 - Zürich HB  22:56 - Zürich Flughafen  23:20 • Winterthur  23:35 - Wil SG  23:52 - Uzwil  00:00 - Flawil  00:05 - Gossau SG  00:10 - St. Gallen  00:18", "7"));
		dtos.add(createDeparture("22:29", "IC 993", "Interlaken Ost", "Olten  22:29 - Bern  22:56 - Thun  23:25 - Spiez  23:35 - Interlaken West  23:54 - Interlaken Ost  23:59", ""));
		dtos.add(createDeparture("22:30", "ICN 682", "Basel SBB", "Olten  22:30 - Basel SBB  22:55", ""));
		dtos.add(createDeparture("22:30", "IC 845", "Romanshorn", "Olten  22:30 - Zürich HB  23:01 - Zürich Flughafen  23:16 - Winterthur  23:33 - Frauenfeld  23:46 - Weinfelden  23:58 - Amriswil  00:10 - Romanshorn  00:18", "7"));
		dtos.add(createDeparture("22:32", "ICE 994", "Basel SBB", "Olten  22:32 - Liestal  22:47 - Basel SBB  22:59", "3"));
		dtos.add(createDeparture("22:35", "IC 844", "Bern", "Olten  22:35 - Bern  23:02", ""));
		dtos.add(createDeparture("22:40", "ICN 1544", "Lausanne", "Olten  22:40 - Solothurn  22:56 - Grenchen Süd  23:05 - Biel/Bienne  23:14 - Neuchâtel  23:32 - Yverdon-les-Bains  23:51 - Lausanne  00:15", ""));
		dtos.add(createDeparture("23:00", "IC 745", "Zürich HB", "Olten  23:00 - Zürich HB  23:31", ""));
		dtos.add(createDeparture("23:04", "IC 744", "Bern", "Olten  23:04 - Bern  23:31", ""));
		dtos.add(createDeparture("23:29", "IC 995", "Bern", "Olten  23:29 - Bern  23:56", ""));
		dtos.add(createDeparture("23:32", "ICE 996", "Basel SBB", "Olten  23:32 - Liestal  23:47 - Basel SBB  23:59", ""));
		dtos.add(createDeparture("23:35", "IC 846", "Bern", "Olten  23:35 - Bern  00:02", ""));
		dtos.add(createDeparture("23:35", "ICN 1547", "St. Gallen", "Olten  23:35 - Aarau  23:43 - Zürich HB  00:10 - Zürich Flughafen  00:27 • Winterthur  00:42 - Wil SG  00:59 - Uzwil  01:06 - Flawil  01:12 - Gossau SG  01:17 - St. Gallen  01:25", "2"));
		dtos.add(createDeparture("23:40", "ICN 1546", "Biel/Bienne", "Olten  23:40 - Oensingen  23:52 - Solothurn  00:04 - Grenchen Süd  00:11 - Biel/Bienne", ""));

	}


//		actionRegistry.register(ValueChangedCommand.class, new CommandHandler<ValueChangedCommand>() {
//			@Override
//			public void handleCommand(final ValueChangedCommand command, final List<Command> response) {
//				Attribute attribute = getServerDolphin().getServerModelStore().findAttributeById(command.getAttributeId());
//				if(attribute != null){
//					if("sharedValue".equals(attribute.getQualifier())){
//						Object newValue = command.getNewValue();
//						eventBus.publish(valueQueue, newValue);
//						waitMillis = EVENT_PROVIDER_WAIT_MS;
//					}
//				}
//			}
//		});

//		actionRegistry.register("poll.value", new CommandHandler<Command>() {
//			@Override
//			public void handleCommand(final Command command, final List<Command> response) {
//				List<Attribute> attributeList = getServerDolphin().getServerModelStore().findAllAttributesByQualifier("sharedValue");
//				ServerAttribute attribute = null;
//				for(Attribute attr : attributeList){
//					if(attr.getTag() == Tag.VALUE){
//						attribute = (ServerAttribute) attr;
//					}
//				}
//				if(attribute == null) {
//					return;
//				}
//
//				try {
//					Object value = valueQueue.getVal(waitMillis, TimeUnit.MILLISECONDS);
//					Object lastValue = value;
//					while (null != value){
//						lastValue = value;
//						value = valueQueue.getVal(20, TimeUnit.MILLISECONDS);
//					}
//					if(null != value) {
//						waitMillis = EVENT_CONSUMER_WAIT_MS;
//						changeValue(attribute, lastValue);
//					}
//				} catch (InterruptedException e) {
//					throw new RuntimeException(e);
//				}
//
//			}
//		});
}