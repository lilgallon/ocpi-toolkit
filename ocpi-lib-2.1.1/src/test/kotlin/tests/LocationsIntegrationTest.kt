package tests

import common.OcpiStatusCode
import ocpi.locations.LocationsCpoServer
import ocpi.locations.LocationsEmspClient
import ocpi.locations.domain.Location
import ocpi.locations.validation.LocationsCpoValidationService
import org.junit.jupiter.api.Test
import org.litote.kmongo.getCollection
import strikt.api.expectThat
import strikt.assertions.*
import tests.mock.LocationsCpoMongoService
import tests.utils.validConnector
import tests.utils.validEvse
import tests.utils.validLocation
import java.time.Instant
import kotlin.math.min

class LocationsIntegrationTest : BaseServerIntegrationTest() {

    @Test
    fun getLocationsTest() {
        // Db setup
        val database = buildDBClient().getDatabase("ocpi-2-1-1-tests")
        val collection = database.getCollection<Location>()
        val locationsCpoService = LocationsCpoMongoService(collection)

        // Add dummy data
        val numberOfLocations = 1000
        val referenceDate = Instant.parse("2022-04-28T09:00:00.000Z")
        val lastDate = referenceDate.plusSeconds(3600L * (numberOfLocations - 1))
        val locations = mutableListOf<Location>()
        (0 until numberOfLocations).forEach { index ->
            locations.add(
                validLocation.copy(
                    evses = listOf(validEvse.copy(connectors = listOf(validConnector))),
                    last_updated = referenceDate.plusSeconds(3600L * index)
                )
            )
        }
        collection.insertMany(locations)

        // Start CPO server
        val server = buildTransportServer()
        val client = server.getClient()

        LocationsCpoServer(server, LocationsCpoValidationService(locationsCpoService))
        server.start()

        val locationsEmspClient = LocationsEmspClient(client)

        // Tests
        var limit = numberOfLocations + 1
        var offset = 0
        var dateFrom: Instant? = null
        var dateTo: Instant? = null

        expectThat(
            locationsEmspClient.getLocations(
                dateFrom = dateFrom,
                dateTo = dateTo,
                offset = offset,
                limit = limit
            )
        ) {
            get { status_code }
                .isEqualTo(OcpiStatusCode.SUCCESS.code)

            get { data }
                .isNotNull()
                .and {
                    get { list }
                        .isNotEmpty()
                        .hasSize(min(limit, numberOfLocations))
                }
                .and {
                    get { limit }
                        .isEqualTo(limit)
                }
                .and {
                    get { offset }
                        .isEqualTo(offset)
                }
                .and {
                    get { totalCount }
                        .isEqualTo(numberOfLocations)
                }
                .and {
                    get { nextPageUrl }
                        .isNull()
                }
        }

        limit = 100
        offset = 100
        dateFrom = null
        dateTo = null

        expectThat(
            locationsEmspClient.getLocations(
                dateFrom = dateFrom,
                dateTo = dateTo,
                offset = offset,
                limit = limit
            )
        ) {
            get { status_code }
                .isEqualTo(OcpiStatusCode.SUCCESS.code)

            get { data }
                .isNotNull()
                .and {
                    get { list }
                        .isNotEmpty()
                        .hasSize(min(limit, numberOfLocations))
                }
                .and {
                    get { limit }
                        .isEqualTo(limit)
                }
                .and {
                    get { offset }
                        .isEqualTo(offset)
                }
                .and {
                    get { totalCount }
                        .isEqualTo(numberOfLocations)
                }
                .and {
                    get { nextPageUrl }
                        .isEqualTo("${server.baseUrl}/ocpi/cpo/2.1.1/locations?limit=$limit&offset=${offset + limit}")
                }
        }

        limit = 50
        offset = 50
        dateFrom = null
        dateTo = null

        expectThat(
            locationsEmspClient.getLocations(
                dateFrom = dateFrom,
                dateTo = dateTo,
                offset = offset,
                limit = limit
            )
        ) {
            get { status_code }
                .isEqualTo(OcpiStatusCode.SUCCESS.code)

            get { data }
                .isNotNull()
                .and {
                    get { list }
                        .isNotEmpty()
                        .hasSize(min(limit, numberOfLocations))
                }
                .and {
                    get { limit }
                        .isEqualTo(limit)
                }
                .and {
                    get { offset }
                        .isEqualTo(offset)
                }
                .and {
                    get { totalCount }
                        .isEqualTo(numberOfLocations)
                }
                .and {
                    get { nextPageUrl }
                        .isEqualTo("${server.baseUrl}/ocpi/cpo/2.1.1/locations?limit=$limit&offset=${offset + limit}")
                }
        }

        limit = numberOfLocations + 1
        offset = 0
        dateFrom = referenceDate
        dateTo = lastDate

        expectThat(
            locationsEmspClient.getLocations(
                dateFrom = dateFrom,
                dateTo = dateTo,
                offset = offset,
                limit = limit
            )
        ) {
            get { status_code }
                .isEqualTo(OcpiStatusCode.SUCCESS.code)

            get { data }
                .isNotNull()
                .and {
                    get { list }
                        .isNotEmpty()
                        .hasSize(min(limit, numberOfLocations))
                }
                .and {
                    get { limit }
                        .isEqualTo(limit)
                }
                .and {
                    get { offset }
                        .isEqualTo(offset)
                }
                .and {
                    get { totalCount }
                        .isEqualTo(numberOfLocations)
                }
                .and {
                    get { nextPageUrl }
                        .isNull()
                }
        }

        limit = numberOfLocations + 1
        offset = 0
        dateFrom = referenceDate
        dateTo = null

        expectThat(
            locationsEmspClient.getLocations(
                dateFrom = dateFrom,
                dateTo = dateTo,
                offset = offset,
                limit = limit
            )
        ) {
            get { status_code }
                .isEqualTo(OcpiStatusCode.SUCCESS.code)

            get { data }
                .isNotNull()
                .and {
                    get { list }
                        .isNotEmpty()
                        .hasSize(min(limit, numberOfLocations))
                }
                .and {
                    get { limit }
                        .isEqualTo(limit)
                }
                .and {
                    get { offset }
                        .isEqualTo(offset)
                }
                .and {
                    get { totalCount }
                        .isEqualTo(numberOfLocations)
                }
                .and {
                    get { nextPageUrl }
                        .isNull()
                }
        }

        limit = numberOfLocations + 1
        offset = 0
        dateFrom = null
        dateTo = lastDate

        expectThat(
            locationsEmspClient.getLocations(
                dateFrom = dateFrom,
                dateTo = dateTo,
                offset = offset,
                limit = limit
            )
        ) {
            get { status_code }
                .isEqualTo(OcpiStatusCode.SUCCESS.code)

            get { data }
                .isNotNull()
                .and {
                    get { list }
                        .isNotEmpty()
                        .hasSize(min(limit, numberOfLocations))
                }
                .and {
                    get { limit }
                        .isEqualTo(limit)
                }
                .and {
                    get { offset }
                        .isEqualTo(offset)
                }
                .and {
                    get { totalCount }
                        .isEqualTo(numberOfLocations)
                }
                .and {
                    get { nextPageUrl }
                        .isNull()
                }
        }

        limit = numberOfLocations + 1
        offset = 0
        dateFrom = lastDate
        dateTo = null

        expectThat(
            locationsEmspClient.getLocations(
                dateFrom = dateFrom,
                dateTo = dateTo,
                offset = offset,
                limit = limit
            )
        ) {
            get { status_code }
                .isEqualTo(OcpiStatusCode.SUCCESS.code)

            get { data }
                .isNotNull()
                .and {
                    get { list }
                        .isNotEmpty()
                        .hasSize(1)
                }
                .and {
                    get { limit }
                        .isEqualTo(limit)
                }
                .and {
                    get { offset }
                        .isEqualTo(offset)
                }
                .and {
                    get { totalCount }
                        .isEqualTo(1)
                }
                .and {
                    get { nextPageUrl }
                        .isNull()
                }
        }

        limit = numberOfLocations + 1
        offset = 0
        dateFrom = null
        dateTo = referenceDate

        expectThat(
            locationsEmspClient.getLocations(
                dateFrom = dateFrom,
                dateTo = dateTo,
                offset = offset,
                limit = limit
            )
        ) {
            get { status_code }
                .isEqualTo(OcpiStatusCode.SUCCESS.code)

            get { data }
                .isNotNull()
                .and {
                    get { list }
                        .isNotEmpty()
                        .hasSize(1)
                }
                .and {
                    get { limit }
                        .isEqualTo(limit)
                }
                .and {
                    get { offset }
                        .isEqualTo(offset)
                }
                .and {
                    get { totalCount }
                        .isEqualTo(1)
                }
                .and {
                    get { nextPageUrl }
                        .isNull()
                }
        }

        limit = numberOfLocations + 1
        offset = 1
        dateFrom = null
        dateTo = referenceDate

        expectThat(
            locationsEmspClient.getLocations(
                dateFrom = dateFrom,
                dateTo = dateTo,
                offset = offset,
                limit = limit
            )
        ) {
            get { status_code }
                .isEqualTo(OcpiStatusCode.SUCCESS.code)

            get { data }
                .isNotNull()
                .and {
                    get { list }
                        .isEmpty()
                }
                .and {
                    get { limit }
                        .isEqualTo(limit)
                }
                .and {
                    get { offset }
                        .isEqualTo(offset)
                }
                .and {
                    get { totalCount }
                        .isEqualTo(1)
                }
                .and {
                    get { nextPageUrl }
                        .isNull()
                }
        }

        limit = 1
        offset = 0
        dateFrom = null
        dateTo = lastDate.minusSeconds(3600L)

        expectThat(
            locationsEmspClient.getLocations(
                dateFrom = dateFrom,
                dateTo = dateTo,
                offset = offset,
                limit = limit
            )
        ) {
            get { status_code }
                .isEqualTo(OcpiStatusCode.SUCCESS.code)

            get { data }
                .isNotNull()
                .and {
                    get { list }
                        .isNotEmpty()
                        .hasSize(1)
                }
                .and {
                    get { limit }
                        .isEqualTo(limit)
                }
                .and {
                    get { offset }
                        .isEqualTo(offset)
                }
                .and {
                    get { totalCount }
                        .isEqualTo(numberOfLocations - 1)
                }
                .and {
                    get { nextPageUrl }
                        .isEqualTo("${server.baseUrl}/ocpi/cpo/2.1.1/locations?date_to=${dateTo}&limit=$limit&offset=${offset + limit}")
                }
        }

        server.stop()
    }
}