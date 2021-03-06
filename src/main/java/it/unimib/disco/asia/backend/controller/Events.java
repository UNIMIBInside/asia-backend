package it.unimib.disco.asia.backend.controller;

import it.unimib.disco.asia.backend.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
public class Events {

	@Autowired
	private EventRepository eventRepo;

	@RequestMapping(value = "events", produces = "application/json")
	public String getEvents(@RequestParam(value = "ids") String idsList,
							 @RequestParam(value = "dates") String datesList,
							 @RequestParam(value = "categories", required = false) String categoriesList) throws Exception {

		String[] geoIds = idsList.split(",");
		String[] dates = datesList.split(",");
		Date[] datesISO = new Date[dates.length];
		for (int i = 0; i < dates.length; i++)
			datesISO[i] = new SimpleDateFormat("yyyy-MM-dd").parse(dates[i]);

		if (categoriesList == null) {
			return eventRepo.eventQuery(geoIds, datesISO).toString();
		} else {
			String[] categories = categoriesList.split(",");
			return eventRepo.eventWithCategoriesQuery(geoIds, datesISO, categories).toString();
		}
	}

}
