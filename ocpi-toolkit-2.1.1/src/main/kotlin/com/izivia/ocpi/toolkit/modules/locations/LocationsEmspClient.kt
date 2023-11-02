package com.izivia.ocpi.toolkit.modules.locations

import com.izivia.ocpi.toolkit.common.*
import com.izivia.ocpi.toolkit.modules.credentials.repositories.PlatformRepository
import com.izivia.ocpi.toolkit.modules.locations.domain.Connector
import com.izivia.ocpi.toolkit.modules.locations.domain.Evse
import com.izivia.ocpi.toolkit.modules.locations.domain.Location
import com.izivia.ocpi.toolkit.modules.versions.domain.ModuleID
import com.izivia.ocpi.toolkit.transport.TransportClient
import com.izivia.ocpi.toolkit.transport.TransportClientBuilder
import com.izivia.ocpi.toolkit.transport.domain.HttpMethod
import com.izivia.ocpi.toolkit.transport.domain.HttpRequest
import java.time.Instant

/**
 * Sends calls to the CPO
 * @property transportClientBuilder used to build transport client
 * @property serverVersionsEndpointUrl used to know which platform to communicate with
 * @property platformRepository used to get information about the platform (endpoint, token)
 */
class LocationsEmspClient(
    private val transportClientBuilder: TransportClientBuilder,
    private val serverVersionsEndpointUrl: String,
    private val platformRepository: PlatformRepository
) : LocationsCpoInterface {

    private fun buildTransport(): TransportClient = transportClientBuilder
        .buildFor(
            module = ModuleID.locations,
            platformUrl = serverVersionsEndpointUrl,
            platformRepository = platformRepository
        )

    override fun getLocations(
        dateFrom: Instant?,
        dateTo: Instant?,
        offset: Int,
        limit: Int?
    ): OcpiResponseBody<SearchResult<Location>> =
        buildTransport()
            .send(
                HttpRequest(
                    method = HttpMethod.GET,
                    queryParams = listOfNotNull(
                        dateFrom?.let { "date_from" to dateFrom.toString() },
                        dateTo?.let { "date_to" to dateTo.toString() },
                        "offset" to offset.toString(),
                        limit?.let { "limit" to limit.toString() }
                    ).toMap()
                ).authenticate(platformRepository = platformRepository, baseUrl = serverVersionsEndpointUrl)
            )
            .parsePaginatedBody(offset)

    override fun getLocation(locationId: String): OcpiResponseBody<Location?> =
        buildTransport()
            .send(
                HttpRequest(
                    method = HttpMethod.GET,
                    path = "/$locationId"
                ).authenticate(platformRepository = platformRepository, baseUrl = serverVersionsEndpointUrl)
            )
            .parseBody()

    override fun getEvse(locationId: String, evseUid: String): OcpiResponseBody<Evse?> =
        buildTransport()
            .send(
                HttpRequest(
                    method = HttpMethod.GET,
                    path = "/$locationId/$evseUid"
                ).authenticate(platformRepository = platformRepository, baseUrl = serverVersionsEndpointUrl)
            )
            .parseBody()

    override fun getConnector(
        locationId: String,
        evseUid: String,
        connectorId: String
    ): OcpiResponseBody<Connector?> =
        buildTransport()
            .send(
                HttpRequest(
                    method = HttpMethod.GET,
                    path = "/$locationId/$evseUid/$connectorId"
                ).authenticate(platformRepository = platformRepository, baseUrl = serverVersionsEndpointUrl)
            )
            .parseBody()
}
