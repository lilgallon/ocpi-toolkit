package ocpi.locations.domain

import io.github.quatresh.annotations.Partial
import java.math.BigDecimal

/**
 * Key-value pairs (enum + amount) of waste and carbon dioxide emittion per kWh.
 * @property category The category of this value.
 * @property amount Amount of this portion in g/kWh.
 */
@Partial
data class EnvironmentalImpact(
    val category: EnvironmentalImpactCategory,
    val amount: BigDecimal
)