package com.izivia.ocpi.toolkit.modules.credentials

import com.izivia.ocpi.toolkit.common.getDebugHeaders
import com.izivia.ocpi.toolkit.common.httpResponse
import com.izivia.ocpi.toolkit.common.mapper
import com.izivia.ocpi.toolkit.common.parseAuthorizationHeader
import com.izivia.ocpi.toolkit.modules.credentials.domain.Credentials
import com.izivia.ocpi.toolkit.modules.credentials.services.CredentialsServerService
import transport.TransportServer
import transport.domain.FixedPathSegment
import transport.domain.HttpMethod

class CredentialsServer(
    transportServer: TransportServer,
    service: CredentialsServerService,
    basePath: List<FixedPathSegment> = listOf(
        FixedPathSegment("/credentials")
    )
) {
    init {
        transportServer.handle(
            method = HttpMethod.GET,
            path = basePath
        ) { req ->
            req.httpResponse {
                service.get(
                    tokenC = req.parseAuthorizationHeader()
                )
            }
        }

        transportServer.handle(
            method = HttpMethod.POST,
            path = basePath
        ) { req ->
            req.httpResponse {
                service.post(
                    tokenA = req.parseAuthorizationHeader(),
                    credentials = mapper.readValue(req.body!!, Credentials::class.java),
                    debugHeaders = req.getDebugHeaders()
                )
            }
        }

        transportServer.handle(
            method = HttpMethod.PUT,
            path = basePath
        ) { req ->
            req.httpResponse {
                service.put(
                    tokenC = req.parseAuthorizationHeader(),
                    credentials = mapper.readValue(req.body!!, Credentials::class.java),
                    debugHeaders = req.getDebugHeaders()
                )
            }
        }

        transportServer.handle(
            method = HttpMethod.DELETE,
            path = basePath
        ) { req ->
            req.httpResponse {
                service.delete(
                    tokenC = req.parseAuthorizationHeader()
                )
            }
        }
    }
}