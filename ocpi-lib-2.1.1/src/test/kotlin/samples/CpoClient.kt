package samples

import ocpi.locations.LocationsCpoClient

/**
 * Example on how to use the CPO client
 */
fun main() {

    // We specify the transport client to communicate with the eMSP
    val transportClient = Http4kTransportClient(emspServerUrl)

    // We instantiate the clients that we want to use
    val locationsCpoClient = LocationsCpoClient(transportClient)

    // We can use it
    locationsCpoClient.getLocation("fr", "abc", "location1")
}