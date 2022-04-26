package samples

import ocpi.locations.LocationsCpoInterface
import ocpi.locations.LocationsCpoServer
import ocpi.locations.domain.Connector
import ocpi.locations.domain.Evse
import ocpi.locations.domain.Location
import java.time.Instant

val cpoServerUrl = "http://localhost:8080"
val cpoServerPort = 8080

/**
 * Example on how to serve a CPO server
 */
fun main() {
    // We specify the transport to serve the cpo server
    val transportServer = Http4kTransportServer(cpoServerPort)

    // We specify callbacks for the server
    val callbacks = LocationsCpoServerCallbacks()

    // We implement callbacks for the server
    LocationsCpoServer(transportServer, callbacks)

    // It is recommended to start the server after setting up the routes to handle
    transportServer.start()
}

class LocationsCpoServerCallbacks : LocationsCpoInterface {
    override fun getLocations(dateFrom: Instant?, dateTo: Instant?, offset: Int?, limit: Int?): List<Location> {
        TODO("Not yet implemented")
    }

    override fun getLocation(locationId: String): Location? {
        TODO("Not yet implemented")
    }

    override fun getLocation(locationId: String, evseUid: String): Evse? {
        TODO("Not yet implemented")
    }

    override fun getLocation(locationId: String, evseUid: String, connectorId: String): Connector? {
        TODO("Not yet implemented")
    }
}