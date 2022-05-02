package tests

import common.OcpiStatusCode
import ocpi.locations.validation.LocationsEmspValidationService
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo
import tests.mock.locationsEmspService
import tests.utils.*

class LocationsEmspValidationServiceTest {
    private lateinit var service: LocationsEmspValidationService

    val str1char = "a"
    val str2chars = "ab"
    val str3chars = "abc"
    val str4chars = "abcd"
    val str36chars = "abababababababababababababababababab"
    val str37chars = "ababababababababababababababababababa"
    val str39chars = "abababababababababababababababababababa"
    val str40chars = "abababababababababababababababababababab"

    @Test
    fun getLocationParamsValidationTest() {
        service = LocationsEmspValidationService(locationsEmspService(emptyList()))

        expectThat(service.getLocation(countryCode = str1char, partyId = str2chars, locationId = str4chars)) {
            get { status_code }
                .isEqualTo(OcpiStatusCode.SUCCESS.code)
        }

        expectThat(service.getLocation(countryCode = str2chars, partyId = str3chars, locationId = str39chars)) {
            get { status_code }
                .isEqualTo(OcpiStatusCode.SUCCESS.code)
        }

        expectThat(service.getLocation(countryCode = str3chars, partyId = str3chars, locationId = str39chars)) {
            get { status_code }
                .isEqualTo(OcpiStatusCode.INVALID_OR_MISSING_PARAMETERS.code)
        }

        expectThat(service.getLocation(countryCode = str2chars, partyId = str4chars, locationId = str39chars)) {
            get { status_code }
                .isEqualTo(OcpiStatusCode.INVALID_OR_MISSING_PARAMETERS.code)
        }

        expectThat(service.getLocation(countryCode = str2chars, partyId = str3chars, locationId = str40chars)) {
            get { status_code }
                .isEqualTo(OcpiStatusCode.INVALID_OR_MISSING_PARAMETERS.code)
        }
    }

    @Test
    fun getEvseParamsValidationTest() {
        service = LocationsEmspValidationService(locationsEmspService(emptyList()))

        expectThat(
            service.getEvse(
                countryCode = str1char,
                partyId = str2chars,
                locationId = str4chars,
                evseUid = str4chars
            )
        ) {
            get { status_code }
                .isEqualTo(OcpiStatusCode.SUCCESS.code)
        }

        expectThat(
            service.getEvse(
                countryCode = str2chars,
                partyId = str3chars,
                locationId = str39chars,
                evseUid = str39chars
            )
        ) {
            get { status_code }
                .isEqualTo(OcpiStatusCode.SUCCESS.code)
        }

        expectThat(
            service.getEvse(
                countryCode = str3chars,
                partyId = str3chars,
                locationId = str39chars,
                evseUid = str39chars
            )
        ) {
            get { status_code }
                .isEqualTo(OcpiStatusCode.INVALID_OR_MISSING_PARAMETERS.code)
        }

        expectThat(
            service.getEvse(
                countryCode = str2chars,
                partyId = str4chars,
                locationId = str39chars,
                evseUid = str39chars
            )
        ) {
            get { status_code }
                .isEqualTo(OcpiStatusCode.INVALID_OR_MISSING_PARAMETERS.code)
        }

        expectThat(
            service.getEvse(
                countryCode = str2chars,
                partyId = str3chars,
                locationId = str40chars,
                evseUid = str39chars
            )
        ) {
            get { status_code }
                .isEqualTo(OcpiStatusCode.INVALID_OR_MISSING_PARAMETERS.code)
        }

        expectThat(
            service.getEvse(
                countryCode = str2chars,
                partyId = str3chars,
                locationId = str39chars,
                evseUid = str40chars
            )
        ) {
            get { status_code }
                .isEqualTo(OcpiStatusCode.INVALID_OR_MISSING_PARAMETERS.code)
        }
    }

    @Test
    fun getConnectorParamsValidationTest() {
        service = LocationsEmspValidationService(locationsEmspService(emptyList()))

        expectThat(
            service.getConnector(
                countryCode = str1char,
                partyId = str2chars,
                locationId = str4chars,
                evseUid = str4chars,
                connectorId = str4chars
            )
        ) {
            get { status_code }
                .isEqualTo(OcpiStatusCode.SUCCESS.code)
        }

        expectThat(
            service.getConnector(
                countryCode = str2chars,
                partyId = str3chars,
                locationId = str39chars,
                evseUid = str39chars,
                connectorId = str36chars
            )
        ) {
            get { status_code }
                .isEqualTo(OcpiStatusCode.SUCCESS.code)
        }

        expectThat(
            service.getConnector(
                countryCode = str3chars,
                partyId = str3chars,
                locationId = str39chars,
                evseUid = str39chars,
                connectorId = str36chars
            )
        ) {
            get { status_code }
                .isEqualTo(OcpiStatusCode.INVALID_OR_MISSING_PARAMETERS.code)
        }

        expectThat(
            service.getConnector(
                countryCode = str2chars,
                partyId = str4chars,
                locationId = str39chars,
                evseUid = str39chars,
                connectorId = str36chars
            )
        ) {
            get { status_code }
                .isEqualTo(OcpiStatusCode.INVALID_OR_MISSING_PARAMETERS.code)
        }

        expectThat(
            service.getConnector(
                countryCode = str2chars,
                partyId = str3chars,
                locationId = str40chars,
                evseUid = str39chars,
                connectorId = str36chars
            )
        ) {
            get { status_code }
                .isEqualTo(OcpiStatusCode.INVALID_OR_MISSING_PARAMETERS.code)
        }

        expectThat(
            service.getConnector(
                countryCode = str2chars,
                partyId = str3chars,
                locationId = str39chars,
                evseUid = str40chars,
                connectorId = str36chars
            )
        ) {
            get { status_code }
                .isEqualTo(OcpiStatusCode.INVALID_OR_MISSING_PARAMETERS.code)
        }

        expectThat(
            service.getConnector(
                countryCode = str2chars,
                partyId = str3chars,
                locationId = str39chars,
                evseUid = str39chars,
                connectorId = str37chars
            )
        ) {
            get { status_code }
                .isEqualTo(OcpiStatusCode.INVALID_OR_MISSING_PARAMETERS.code)
        }
    }

    @Test
    fun patchLocationParamsValidationTest() {
        service = LocationsEmspValidationService(locationsEmspService(emptyList()))

        expectThat(
            service.patchLocation(
                countryCode = str1char,
                partyId = str2chars,
                locationId = str4chars,
                location = validLocationPartial
            )
        ) {
            get { status_code }
                .isEqualTo(OcpiStatusCode.SUCCESS.code)
        }

        expectThat(
            service.patchLocation(
                countryCode = str2chars,
                partyId = str3chars,
                locationId = str39chars,
                location = validLocationPartial
            )
        ) {
            get { status_code }
                .isEqualTo(OcpiStatusCode.SUCCESS.code)
        }

        expectThat(
            service.patchLocation(
                countryCode = str3chars,
                partyId = str3chars,
                locationId = str39chars,
                location = validLocationPartial
            )
        ) {
            get { status_code }
                .isEqualTo(OcpiStatusCode.INVALID_OR_MISSING_PARAMETERS.code)
        }

        expectThat(
            service.patchLocation(
                countryCode = str2chars,
                partyId = str4chars,
                locationId = str39chars,
                location = validLocationPartial
            )
        ) {
            get { status_code }
                .isEqualTo(OcpiStatusCode.INVALID_OR_MISSING_PARAMETERS.code)
        }

        expectThat(
            service.patchLocation(
                countryCode = str2chars,
                partyId = str3chars,
                locationId = str40chars,
                location = validLocationPartial
            )
        ) {
            get { status_code }
                .isEqualTo(OcpiStatusCode.INVALID_OR_MISSING_PARAMETERS.code)
        }
    }

    @Test
    fun patchEvseParamsValidationTest() {
        service = LocationsEmspValidationService(locationsEmspService(emptyList()))

        expectThat(
            service.patchEvse(
                countryCode = str1char,
                partyId = str2chars,
                locationId = str4chars,
                evseUid = str4chars,
                evse = validEvsePartial
            )
        ) {
            get { status_code }
                .isEqualTo(OcpiStatusCode.SUCCESS.code)
        }

        expectThat(
            service.patchEvse(
                countryCode = str2chars,
                partyId = str3chars,
                locationId = str39chars,
                evseUid = str39chars,
                evse = validEvsePartial
            )
        ) {
            get { status_code }
                .isEqualTo(OcpiStatusCode.SUCCESS.code)
        }

        expectThat(
            service.patchEvse(
                countryCode = str3chars,
                partyId = str3chars,
                locationId = str39chars,
                evseUid = str39chars,
                evse = validEvsePartial
            )
        ) {
            get { status_code }
                .isEqualTo(OcpiStatusCode.INVALID_OR_MISSING_PARAMETERS.code)
        }

        expectThat(
            service.patchEvse(
                countryCode = str2chars,
                partyId = str4chars,
                locationId = str39chars,
                evseUid = str39chars,
                evse = validEvsePartial
            )
        ) {
            get { status_code }
                .isEqualTo(OcpiStatusCode.INVALID_OR_MISSING_PARAMETERS.code)
        }

        expectThat(
            service.patchEvse(
                countryCode = str2chars,
                partyId = str3chars,
                locationId = str40chars,
                evseUid = str39chars,
                evse = validEvsePartial
            )
        ) {
            get { status_code }
                .isEqualTo(OcpiStatusCode.INVALID_OR_MISSING_PARAMETERS.code)
        }

        expectThat(
            service.patchEvse(
                countryCode = str2chars,
                partyId = str3chars,
                locationId = str39chars,
                evseUid = str40chars,
                evse = validEvsePartial
            )
        ) {
            get { status_code }
                .isEqualTo(OcpiStatusCode.INVALID_OR_MISSING_PARAMETERS.code)
        }
    }

    @Test
    fun patchConnectorParamsValidationTest() {
        service = LocationsEmspValidationService(locationsEmspService(emptyList()))

        expectThat(
            service.patchConnector(
                countryCode = str1char,
                partyId = str2chars,
                locationId = str4chars,
                evseUid = str4chars,
                connectorId = str4chars,
                connector = validConnectorPartial
            )
        ) {
            get { status_code }
                .isEqualTo(OcpiStatusCode.SUCCESS.code)
        }

        expectThat(
            service.patchConnector(
                countryCode = str2chars,
                partyId = str3chars,
                locationId = str39chars,
                evseUid = str39chars,
                connectorId = str36chars,
                connector = validConnectorPartial
            )
        ) {
            get { status_code }
                .isEqualTo(OcpiStatusCode.SUCCESS.code)
        }

        expectThat(
            service.patchConnector(
                countryCode = str3chars,
                partyId = str3chars,
                locationId = str39chars,
                evseUid = str39chars,
                connectorId = str36chars,
                connector = validConnectorPartial
            )
        ) {
            get { status_code }
                .isEqualTo(OcpiStatusCode.INVALID_OR_MISSING_PARAMETERS.code)
        }

        expectThat(
            service.patchConnector(
                countryCode = str2chars,
                partyId = str4chars,
                locationId = str39chars,
                evseUid = str39chars,
                connectorId = str36chars,
                connector = validConnectorPartial
            )
        ) {
            get { status_code }
                .isEqualTo(OcpiStatusCode.INVALID_OR_MISSING_PARAMETERS.code)
        }

        expectThat(
            service.patchConnector(
                countryCode = str2chars,
                partyId = str3chars,
                locationId = str40chars,
                evseUid = str39chars,
                connectorId = str36chars,
                connector = validConnectorPartial
            )
        ) {
            get { status_code }
                .isEqualTo(OcpiStatusCode.INVALID_OR_MISSING_PARAMETERS.code)
        }

        expectThat(
            service.patchConnector(
                countryCode = str2chars,
                partyId = str3chars,
                locationId = str39chars,
                evseUid = str40chars,
                connectorId = str36chars,
                connector = validConnectorPartial
            )
        ) {
            get { status_code }
                .isEqualTo(OcpiStatusCode.INVALID_OR_MISSING_PARAMETERS.code)
        }

        expectThat(
            service.patchConnector(
                countryCode = str2chars,
                partyId = str3chars,
                locationId = str39chars,
                evseUid = str39chars,
                connectorId = str37chars,
                connector = validConnectorPartial
            )
        ) {
            get { status_code }
                .isEqualTo(OcpiStatusCode.INVALID_OR_MISSING_PARAMETERS.code)
        }
    }

    @Test
    fun putLocationParamsValidationTest() {
        service = LocationsEmspValidationService(locationsEmspService(emptyList()))

        expectThat(
            service.putLocation(
                countryCode = str1char,
                partyId = str2chars,
                locationId = str4chars,
                location = validLocation
            )
        ) {
            get { status_code }
                .isEqualTo(OcpiStatusCode.SUCCESS.code)
        }

        expectThat(
            service.putLocation(
                countryCode = str2chars,
                partyId = str3chars,
                locationId = str39chars,
                location = validLocation
            )
        ) {
            get { status_code }
                .isEqualTo(OcpiStatusCode.SUCCESS.code)
        }

        expectThat(
            service.putLocation(
                countryCode = str3chars,
                partyId = str3chars,
                locationId = str39chars,
                location = validLocation
            )
        ) {
            get { status_code }
                .isEqualTo(OcpiStatusCode.INVALID_OR_MISSING_PARAMETERS.code)
        }

        expectThat(
            service.putLocation(
                countryCode = str2chars,
                partyId = str4chars,
                locationId = str39chars,
                location = validLocation
            )
        ) {
            get { status_code }
                .isEqualTo(OcpiStatusCode.INVALID_OR_MISSING_PARAMETERS.code)
        }

        expectThat(
            service.putLocation(
                countryCode = str2chars,
                partyId = str3chars,
                locationId = str40chars,
                location = validLocation
            )
        ) {
            get { status_code }
                .isEqualTo(OcpiStatusCode.INVALID_OR_MISSING_PARAMETERS.code)
        }
    }

    @Test
    fun putEvseParamsValidationTest() {
        service = LocationsEmspValidationService(locationsEmspService(emptyList()))

        expectThat(
            service.putEvse(
                countryCode = str1char,
                partyId = str2chars,
                locationId = str4chars,
                evseUid = str4chars,
                evse = validEvse
            )
        ) {
            get { status_code }
                .isEqualTo(OcpiStatusCode.SUCCESS.code)
        }

        expectThat(
            service.putEvse(
                countryCode = str2chars,
                partyId = str3chars,
                locationId = str39chars,
                evseUid = str39chars,
                evse = validEvse
            )
        ) {
            get { status_code }
                .isEqualTo(OcpiStatusCode.SUCCESS.code)
        }

        expectThat(
            service.putEvse(
                countryCode = str3chars,
                partyId = str3chars,
                locationId = str39chars,
                evseUid = str39chars,
                evse = validEvse
            )
        ) {
            get { status_code }
                .isEqualTo(OcpiStatusCode.INVALID_OR_MISSING_PARAMETERS.code)
        }

        expectThat(
            service.putEvse(
                countryCode = str2chars,
                partyId = str4chars,
                locationId = str39chars,
                evseUid = str39chars,
                evse = validEvse
            )
        ) {
            get { status_code }
                .isEqualTo(OcpiStatusCode.INVALID_OR_MISSING_PARAMETERS.code)
        }

        expectThat(
            service.putEvse(
                countryCode = str2chars,
                partyId = str3chars,
                locationId = str40chars,
                evseUid = str39chars,
                evse = validEvse
            )
        ) {
            get { status_code }
                .isEqualTo(OcpiStatusCode.INVALID_OR_MISSING_PARAMETERS.code)
        }

        expectThat(
            service.putEvse(
                countryCode = str2chars,
                partyId = str3chars,
                locationId = str39chars,
                evseUid = str40chars,
                evse = validEvse
            )
        ) {
            get { status_code }
                .isEqualTo(OcpiStatusCode.INVALID_OR_MISSING_PARAMETERS.code)
        }
    }

    @Test
    fun putConnectorParamsValidationTest() {
        service = LocationsEmspValidationService(locationsEmspService(emptyList()))

        expectThat(
            service.putConnector(
                countryCode = str1char,
                partyId = str2chars,
                locationId = str4chars,
                evseUid = str4chars,
                connectorId = str4chars,
                connector = validConnector
            )
        ) {
            get { status_code }
                .isEqualTo(OcpiStatusCode.SUCCESS.code)
        }

        expectThat(
            service.putConnector(
                countryCode = str2chars,
                partyId = str3chars,
                locationId = str39chars,
                evseUid = str39chars,
                connectorId = str36chars,
                connector = validConnector
            )
        ) {
            get { status_code }
                .isEqualTo(OcpiStatusCode.SUCCESS.code)
        }

        expectThat(
            service.putConnector(
                countryCode = str3chars,
                partyId = str3chars,
                locationId = str39chars,
                evseUid = str39chars,
                connectorId = str36chars,
                connector = validConnector
            )
        ) {
            get { status_code }
                .isEqualTo(OcpiStatusCode.INVALID_OR_MISSING_PARAMETERS.code)
        }

        expectThat(
            service.putConnector(
                countryCode = str2chars,
                partyId = str4chars,
                locationId = str39chars,
                evseUid = str39chars,
                connectorId = str36chars,
                connector = validConnector
            )
        ) {
            get { status_code }
                .isEqualTo(OcpiStatusCode.INVALID_OR_MISSING_PARAMETERS.code)
        }

        expectThat(
            service.putConnector(
                countryCode = str2chars,
                partyId = str3chars,
                locationId = str40chars,
                evseUid = str39chars,
                connectorId = str36chars,
                connector = validConnector
            )
        ) {
            get { status_code }
                .isEqualTo(OcpiStatusCode.INVALID_OR_MISSING_PARAMETERS.code)
        }

        expectThat(
            service.putConnector(
                countryCode = str2chars,
                partyId = str3chars,
                locationId = str39chars,
                evseUid = str40chars,
                connectorId = str36chars,
                connector = validConnector
            )
        ) {
            get { status_code }
                .isEqualTo(OcpiStatusCode.INVALID_OR_MISSING_PARAMETERS.code)
        }

        expectThat(
            service.putConnector(
                countryCode = str2chars,
                partyId = str3chars,
                locationId = str39chars,
                evseUid = str39chars,
                connectorId = str37chars,
                connector = validConnector
            )
        ) {
            get { status_code }
                .isEqualTo(OcpiStatusCode.INVALID_OR_MISSING_PARAMETERS.code)
        }
    }
}