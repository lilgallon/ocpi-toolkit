package samples.credentials

import ocpi.credentials.services.CredentialsClientService
import ocpi.locations.domain.BusinessDetails
import ocpi.versions.VersionDetailsServer
import ocpi.versions.VersionsServer
import ocpi.versions.validation.VersionDetailsValidationService
import ocpi.versions.validation.VersionsValidationService
import samples.common.*

const val senderPort = 8081
const val senderUrl = "http://localhost:$senderPort"
const val senderVersionsUrl = "http://localhost:$senderPort/versions"

fun main() {
    // Server
    val senderServer = Http4kTransportServer(baseUrl = senderUrl, port = senderPort)

    // Add token A associated with the sender
    val senderVersionsRepository = VersionsCacheRepository(baseUrl = senderUrl)
    val senderVersionDetailsRepository = VersionDetailsCacheRepository(baseUrl = senderUrl)
    val senderPlatformRepository = PlatformCacheRepository()
    senderPlatformRepository.platforms[receiverVersionsUrl] = Platform(url = receiverVersionsUrl, tokenA = tokenA)

    VersionsServer(
        transportServer = senderServer,
        platformRepository = senderPlatformRepository,
        validationService = VersionsValidationService(
            repository = senderVersionsRepository
        )
    )
    VersionDetailsServer(
        transportServer = senderServer,
        platformRepository = senderPlatformRepository,
        validationService = VersionDetailsValidationService(
            repository = senderVersionDetailsRepository
        )
    )
    senderServer.start()

    // Client

    val credentialsClientService = CredentialsClientService(
        clientVersionsEndpointUrl = senderVersionsUrl,
        clientPlatformRepository = senderPlatformRepository,
        clientVersionsRepository = senderVersionsRepository,
        clientBusinessDetails = BusinessDetails(name = "Sender", website = null, logo = null),
        clientPartyId = "ABC",
        clientCountryCode = "FR",
        serverVersionsEndpointUrl = receiverVersionsUrl,
        transportClientBuilder = Http4kTransportClientBuilder()
    )

    println("Registering $senderUrl to $receiverUrl")
    var credentials = credentialsClientService.register()
    println("Success. Credentials after register : $credentials")

    println("Retrieving credentials from $receiverUrl...")
    credentials = credentialsClientService.get()
    println("Success. Credentials : $credentials")

    println("Deleting credentials of $senderUrl on $receiverUrl...")
    credentialsClientService.delete()
    println("Success.")
}