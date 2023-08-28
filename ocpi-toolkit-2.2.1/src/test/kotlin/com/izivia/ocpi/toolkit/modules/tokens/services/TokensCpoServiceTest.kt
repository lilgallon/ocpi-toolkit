package com.izivia.ocpi.toolkit.modules.tokens.services

import com.izivia.ocpi.toolkit.common.OcpiStatus
import com.izivia.ocpi.toolkit.modules.tokens.domain.TokenType
import com.izivia.ocpi.toolkit.modules.tokens.domain.toPartial
import com.izivia.ocpi.toolkit.modules.tokens.mock.tokensCpoRepositoryTest
import com.izivia.ocpi.toolkit.modules.tokens.mock.validTokenFullRfid
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo


class TokensCpoServiceTest {
    private lateinit var service: TokensCpoService

    private val str1char = "a"
    private val str2chars = "ab"
    private val str3chars = "abc"
    private val str4chars = "abcd"
    private val str36chars = "abababababababababababababababababab"
    private val str37chars = "ababababababababababababababababababa"
    private val str40chars = "abababababababababababababababababababab"

    @Test
    fun getTokenParamsValidationTest() {
        service = TokensCpoService(service = tokensCpoRepositoryTest(validTokenFullRfid))

        expectThat(service.getToken(countryCode = str2chars, partyId = str3chars, tokenUid = str36chars)) {
            get { status_code }.isEqualTo(OcpiStatus.SUCCESS.code)
        }

        expectThat(
            service.getToken(countryCode = str2chars, partyId = str3chars, tokenUid = str36chars, type = TokenType.RFID)
        ) {
            get { status_code }.isEqualTo(OcpiStatus.SUCCESS.code)
        }

        expectThat(
            service.getToken(countryCode = str2chars, partyId = str3chars, tokenUid = str37chars, type = TokenType.RFID)
        ) {
            get { status_code }.isEqualTo(OcpiStatus.CLIENT_INVALID_PARAMETERS.code)
        }
        expectThat(
            service.getToken(countryCode = str3chars, partyId = str3chars, tokenUid = str36chars, type = TokenType.RFID)
        ) {
            get { status_code }.isEqualTo(OcpiStatus.CLIENT_INVALID_PARAMETERS.code)
        }
        expectThat(
            service.getToken(countryCode = str2chars, partyId = str4chars, tokenUid = str36chars, type = TokenType.RFID)
        ) {
            get { status_code }.isEqualTo(OcpiStatus.CLIENT_INVALID_PARAMETERS.code)
        }
    }

    @Test
    fun putTokenParamsValidationTest() {
        service = TokensCpoService(service = tokensCpoRepositoryTest(validTokenFullRfid))

        expectThat(
            service.putToken(
                token = validTokenFullRfid,
                countryCode = str2chars,
                partyId = str3chars,
                tokenUid = str4chars,
            )
        ) {
            get { status_code }.isEqualTo(OcpiStatus.SUCCESS.code)
        }

        expectThat(
            service.putToken(
                token = validTokenFullRfid,
                countryCode = str2chars,
                partyId = str3chars,
                tokenUid = str36chars,
                type = TokenType.RFID
            )
        ) {
            get { status_code }.isEqualTo(OcpiStatus.SUCCESS.code)
        }

        expectThat(
            service.putToken(
                token = validTokenFullRfid,
                countryCode = str3chars,
                partyId = str3chars,
                tokenUid = str36chars,
                type = TokenType.RFID
            )
        ) {
            get { status_code }.isEqualTo(OcpiStatus.CLIENT_INVALID_PARAMETERS.code)
        }

        expectThat(
            service.putToken(
                token = validTokenFullRfid,
                countryCode = str2chars,
                partyId = str4chars,
                tokenUid = str36chars,
                type = TokenType.RFID
            )
        ) {
            get { status_code }.isEqualTo(OcpiStatus.CLIENT_INVALID_PARAMETERS.code)
        }

        expectThat(
            service.putToken(
                token = validTokenFullRfid,
                countryCode = str2chars,
                partyId = str3chars,
                tokenUid = str40chars,
                type = TokenType.RFID
            )
        ) {
            get { status_code }.isEqualTo(OcpiStatus.CLIENT_INVALID_PARAMETERS.code)
        }
    }

    @Test
    fun patchTokenParamsValidationTest() {
        service = TokensCpoService(service = tokensCpoRepositoryTest(validTokenFullRfid))

        expectThat(
            service.patchToken(
                token = validTokenFullRfid.toPartial(),
                countryCode = str2chars,
                partyId = str3chars,
                tokenUid = str4chars,
            )
        ) {
            get { status_code }.isEqualTo(OcpiStatus.SUCCESS.code)
        }

        expectThat(
            service.patchToken(
                token = validTokenFullRfid.toPartial(),
                countryCode = str2chars,
                partyId = str3chars,
                tokenUid = str36chars,
                type = TokenType.RFID
            )
        ) {
            get { status_code }.isEqualTo(OcpiStatus.SUCCESS.code)
        }

        expectThat(
            service.patchToken(
                token = validTokenFullRfid.toPartial(),
                countryCode = str3chars,
                partyId = str3chars,
                tokenUid = str36chars,
                type = TokenType.RFID
            )
        ) {
            get { status_code }.isEqualTo(OcpiStatus.CLIENT_INVALID_PARAMETERS.code)
        }

        expectThat(
            service.patchToken(
                token = validTokenFullRfid.toPartial(),
                countryCode = str2chars,
                partyId = str4chars,
                tokenUid = str36chars,
                type = TokenType.RFID
            )
        ) {
            get { status_code }.isEqualTo(OcpiStatus.CLIENT_INVALID_PARAMETERS.code)
        }

        expectThat(
            service.patchToken(
                token = validTokenFullRfid.toPartial(),
                countryCode = str2chars,
                partyId = str3chars,
                tokenUid = str40chars,
                type = TokenType.RFID
            )
        ) {
            get { status_code }.isEqualTo(OcpiStatus.CLIENT_INVALID_PARAMETERS.code)
        }
    }
}
