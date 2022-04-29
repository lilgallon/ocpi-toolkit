package ocpi.locations.services

import common.OcpiResponseBody
import common.validate
import ocpi.locations.LocationsEmspInterface
import ocpi.locations.domain.*
import ocpi.locations.repositories.LocationsEmspRepository
import ocpi.locations.validators.validate

class LocationsEmspService(
    private val repository: LocationsEmspRepository
) : LocationsEmspInterface {

    override fun getLocation(
        countryCode: String,
        partyId: String,
        locationId: String
    ): OcpiResponseBody<Location?> = OcpiResponseBody.of {
        validate {
            // TODO
        }

        repository
            .getLocation(countryCode = countryCode, partyId = partyId, locationId = locationId)
            ?.validate()
    }


    override fun getEvse(
        countryCode: String,
        partyId: String,
        locationId: String,
        evseUid: String
    ): OcpiResponseBody<Evse?> = OcpiResponseBody.of {
        validate {
            // TODO
        }

        repository
            .getEvse(countryCode = countryCode, partyId = partyId, locationId = locationId, evseUid = evseUid)
            ?.validate()
    }

    override fun getConnector(
        countryCode: String,
        partyId: String,
        locationId: String,
        evseUid: String,
        connectorId: String
    ): OcpiResponseBody<Connector?> = OcpiResponseBody.of {
        validate {
            // TODO
        }

        repository
            .getConnector(
                countryCode = countryCode,
                partyId = partyId,
                locationId = locationId,
                evseUid = evseUid,
                connectorId = connectorId
            )
            ?.validate()
    }

    override fun putLocation(
        countryCode: String,
        partyId: String,
        locationId: String,
        location: Location
    ): OcpiResponseBody<Location> = OcpiResponseBody.of {
        validate {
            // TODO
        }

        repository
            .putLocation(countryCode = countryCode, partyId = partyId, locationId = locationId, location = location)
            .validate()
    }

    override fun putEvse(
        countryCode: String,
        partyId: String,
        locationId: String,
        evseUid: String,
        evse: Evse
    ): OcpiResponseBody<Evse> = OcpiResponseBody.of {
        validate {
            // TODO
        }

        repository
            .putEvse(
                countryCode = countryCode,
                partyId = partyId,
                locationId = locationId,
                evseUid = evseUid,
                evse = evse
            )
            .validate()
    }

    override fun putConnector(
        countryCode: String,
        partyId: String,
        locationId: String,
        evseUid: String,
        connectorId: String,
        connector: Connector
    ): OcpiResponseBody<Connector> = OcpiResponseBody.of {
        validate {
            // TODO
        }

        repository
            .putConnector(
                countryCode = countryCode,
                partyId = partyId,
                locationId = locationId,
                evseUid = evseUid,
                connectorId = connectorId,
                connector = connector
            )
            .validate()
    }

    override fun patchLocation(
        countryCode: String,
        partyId: String,
        locationId: String,
        location: LocationPartial
    ): OcpiResponseBody<Location?> = OcpiResponseBody.of {
        validate {
            // TODO
        }

        repository
            .patchLocation(
                countryCode = countryCode,
                partyId = partyId,
                locationId = locationId,
                location = location
            )
            ?.validate()
    }

    override fun patchEvse(
        countryCode: String,
        partyId: String,
        locationId: String,
        evseUid: String,
        evse: EvsePartial
    ): OcpiResponseBody<Evse?> = OcpiResponseBody.of {
        validate {
            // TODO
        }

        repository
            .patchEvse(
                countryCode = countryCode,
                partyId = partyId,
                locationId = locationId,
                evseUid = evseUid,
                evse = evse
            )
            ?.validate()
    }

    override fun patchConnector(
        countryCode: String,
        partyId: String,
        locationId: String,
        evseUid: String,
        connectorId: String,
        connector: ConnectorPartial
    ): OcpiResponseBody<Connector?> = OcpiResponseBody.of {
        validate {
            // TODO
        }

        repository
            .patchConnector(
                countryCode = countryCode,
                partyId = partyId,
                locationId = locationId,
                evseUid = evseUid,
                connectorId = connectorId,
                connector = connector
            )
            ?.validate()
    }
}