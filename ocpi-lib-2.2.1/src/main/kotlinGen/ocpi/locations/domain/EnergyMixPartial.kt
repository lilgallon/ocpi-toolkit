//  ----------
//  - WARNING -
//  ----------
//  This code is generated AND MUST NOT BE EDITED
//  ----------
package ocpi.locations.domain

import kotlin.Boolean
import kotlin.String
import kotlin.collections.List

/**
 * Partial representation of [ocpi.locations.domain.EnergyMix]
 */
public data class EnergyMixPartial(
  public val is_green_energy: Boolean?,
  public val energy_sources: List<EnergySource>?,
  public val environ_impact: List<EnvironmentalImpact>?,
  public val supplier_name: String?,
  public val energy_product_name: String?,
)

public fun EnergyMix.toPartial(): EnergyMixPartial {
   return EnergyMixPartial(
     is_green_energy = is_green_energy,
    energy_sources = energy_sources,
    environ_impact = environ_impact,
    supplier_name = supplier_name,
    energy_product_name = energy_product_name
   )
}

public fun List<EnergyMix>.toPartial(): List<EnergyMixPartial> {
   return mapNotNull { it.toPartial() }
}