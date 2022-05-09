package ocpi.locations.validation

import common.OcpiResponseBody
import common.validate
import common.validateLength
import common.validateToken
import ocpi.credentials.repositories.PlatformRepository
import ocpi.locations.LocationsEmspInterface
import ocpi.locations.domain.*
import ocpi.locations.services.LocationsEmspService

class LocationsEmspValidationService(
    private val service: LocationsEmspService,
    private val platformRepository: PlatformRepository
) : LocationsEmspInterface {

    override fun getLocation(
        token: String,
        countryCode: String,
        partyId: String,
        locationId: String
    ): OcpiResponseBody<Location?> = OcpiResponseBody.of {
        validateToken(platformRepository = platformRepository, token = token)

        validate {
            validateLength("countryCode", countryCode, 2)
            validateLength("partyId", partyId, 3)
            validateLength("locationId", locationId, 39)
        }

        service
            .getLocation(countryCode = countryCode, partyId = partyId, locationId = locationId)
            ?.validate()
    }


    override fun getEvse(
        token: String,
        countryCode: String,
        partyId: String,
        locationId: String,
        evseUid: String
    ): OcpiResponseBody<Evse?> = OcpiResponseBody.of {
        validateToken(platformRepository = platformRepository, token = token)

        validate {
            validateLength("countryCode", countryCode, 2)
            validateLength("partyId", partyId, 3)
            validateLength("locationId", locationId, 39)
            validateLength("evseUid", evseUid, 39)
        }

        service
            .getEvse(countryCode = countryCode, partyId = partyId, locationId = locationId, evseUid = evseUid)
            ?.validate()
    }

    override fun getConnector(
        token: String,
        countryCode: String,
        partyId: String,
        locationId: String,
        evseUid: String,
        connectorId: String
    ): OcpiResponseBody<Connector?> = OcpiResponseBody.of {
        validateToken(platformRepository = platformRepository, token = token)

        validate {
            validateLength("countryCode", countryCode, 2)
            validateLength("partyId", partyId, 3)
            validateLength("locationId", locationId, 39)
            validateLength("evseUid", evseUid, 39)
            validateLength("connectorId", connectorId, 36)
        }

        service
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
        token: String,
        countryCode: String,
        partyId: String,
        locationId: String,
        location: Location
    ): OcpiResponseBody<Location> = OcpiResponseBody.of {
        validateToken(platformRepository = platformRepository, token = token)

        validate {
            validateLength("countryCode", countryCode, 2)
            validateLength("partyId", partyId, 3)
            validateLength("locationId", locationId, 39)
            location.validate()
        }

        service
            .putLocation(countryCode = countryCode, partyId = partyId, locationId = locationId, location = location)
            .validate()
    }

    override fun putEvse(
        token: String,
        countryCode: String,
        partyId: String,
        locationId: String,
        evseUid: String,
        evse: Evse
    ): OcpiResponseBody<Evse> = OcpiResponseBody.of {
        validateToken(platformRepository = platformRepository, token = token)

        validate {
            validateLength("countryCode", countryCode, 2)
            validateLength("partyId", partyId, 3)
            validateLength("locationId", locationId, 39)
            validateLength("evseUid", evseUid, 39)
            evse.validate()
        }

        service
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
        token: String,
        countryCode: String,
        partyId: String,
        locationId: String,
        evseUid: String,
        connectorId: String,
        connector: Connector
    ): OcpiResponseBody<Connector> = OcpiResponseBody.of {
        validateToken(platformRepository = platformRepository, token = token)

        validate {
            validateLength("countryCode", countryCode, 2)
            validateLength("partyId", partyId, 3)
            validateLength("locationId", locationId, 39)
            validateLength("evseUid", evseUid, 39)
            validateLength("connectorId", connectorId, 36)
            connector.validate()
        }

        service
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
        token: String,
        countryCode: String,
        partyId: String,
        locationId: String,
        location: LocationPartial
    ): OcpiResponseBody<Location?> = OcpiResponseBody.of {
        validateToken(platformRepository = platformRepository, token = token)

        validate {
            validateLength("countryCode", countryCode, 2)
            validateLength("partyId", partyId, 3)
            validateLength("locationId", locationId, 39)
            location.validate()
        }

        service
            .patchLocation(
                countryCode = countryCode,
                partyId = partyId,
                locationId = locationId,
                location = location
            )
            ?.validate()
    }

    override fun patchEvse(
        token: String,
        countryCode: String,
        partyId: String,
        locationId: String,
        evseUid: String,
        evse: EvsePartial
    ): OcpiResponseBody<Evse?> = OcpiResponseBody.of {
        validateToken(platformRepository = platformRepository, token = token)

        validate {
            validateLength("countryCode", countryCode, 2)
            validateLength("partyId", partyId, 3)
            validateLength("locationId", locationId, 39)
            validateLength("evseUid", evseUid, 39)
            evse.validate()
        }

        service
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
        token: String,
        countryCode: String,
        partyId: String,
        locationId: String,
        evseUid: String,
        connectorId: String,
        connector: ConnectorPartial
    ): OcpiResponseBody<Connector?> = OcpiResponseBody.of {
        validateToken(platformRepository = platformRepository, token = token)

        validate {
            validateLength("countryCode", countryCode, 2)
            validateLength("partyId", partyId, 3)
            validateLength("locationId", locationId, 39)
            validateLength("evseUid", evseUid, 39)
            validateLength("connectorId", connectorId, 36)
            connector.validate()
        }

        service
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