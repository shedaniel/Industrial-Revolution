package me.steven.indrev.registry

import io.github.cottonmc.resources.type.GenericResourceType
import me.steven.indrev.fluids.CoolantFluid
import me.steven.indrev.items.*
import me.steven.indrev.items.rechargeable.IRRechargeableItem
import me.steven.indrev.items.rechargeable.IRRechargeableMiningItem
import me.steven.indrev.items.upgrade.IRUpgradeItem
import me.steven.indrev.items.upgrade.Upgrade
import me.steven.indrev.utils.*
import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.minecraft.block.Block
import net.minecraft.block.FluidBlock
import net.minecraft.block.Material
import net.minecraft.item.BucketItem
import net.minecraft.item.Item
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry

object ModRegistry {

    fun registerAll() {
        identifier("hammer").item(HAMMER)

        NIKOLITE.registerAll()
        identifier("enriched_nikolite").item(DEFAULT_ITEM())
        identifier("enriched_nikolite_ingot").item(DEFAULT_ITEM())

        identifier("mining_drill").item(MINING_DRILL)
        identifier("battery").item(IRRechargeableItem(itemSettings().maxDamage(4096), true))
        identifier("circuit").tierBasedItem { DEFAULT_ITEM() }

        identifier("machine_block").item(DEFAULT_ITEM())

        identifier("fan").item(FAN)
        identifier("cooler_cell").item(COOLER_CELL)

        identifier("uranium_rod").item(URANIUM_ROD_ITEM)

        identifier("chunk_scanner").item(IRChunkScannerItem(itemSettings()))

        identifier("empty_upgrade").item(DEFAULT_ITEM())
        identifier("buffer_upgrade").item(BUFFER_UPGRADE)
        identifier("speed_upgrade").item(SPEED_UPGRADE)
        identifier("energy_upgrade").item(ENERGY_UPGRADE)

        identifier("energy_reader").item(ENERGY_READER)

        identifier("area_indicator").block(AREA_INDICATOR)

        identifier("tier_upgrade_mk2").item(IRMachineUpgradeItem(itemSettings(), Tier.MK1, Tier.MK2))
        identifier("tier_upgrade_mk3").item(IRMachineUpgradeItem(itemSettings(), Tier.MK2, Tier.MK3))
        identifier("tier_upgrade_mk4").item(IRMachineUpgradeItem(itemSettings(), Tier.MK3, Tier.MK4))

        identifier("biomass").item(BIOMASS)

        identifier("coolant").block(COOLANT)
        identifier("coolant_still").fluid(COOLANT_FLUID_STILL)
        identifier("coolant_flowing").fluid(COOLANT_FLUID_FLOWING)
        identifier("coolant_bucket").item(COOLANT_BUCKET)
    }

    private val DEFAULT_ITEM: () -> Item = { Item(itemSettings()) }

    val HAMMER = IRCraftingToolItem(itemSettings().maxDamage(32))

    val NIKOLITE = GenericResourceType.Builder("nikolite")
        .allOres()
        .withDustAffix()
        .noBlock()
        .build()
        .withItemAffixes("ingot")

    val COPPER_ORE = Registry.BLOCK.get(Identifier("c:copper_ore"))
    val TIN_ORE = Registry.BLOCK.get(Identifier("c:copper_ore"))

    val BIOMASS = DEFAULT_ITEM()

    val FAN = IRCoolerItem(itemSettings().maxDamage(512), -0.07)
    val COOLER_CELL = IRCoolerItem(itemSettings().maxDamage(256), -0.1)

    val URANIUM_ROD_ITEM = Item(itemSettings().maxDamage(1024))

    val MINING_DRILL = IRRechargeableMiningItem(itemSettings().maxDamage(32000))

    val ENERGY_READER = IREnergyReader(itemSettings())

    val AREA_INDICATOR = Block(FabricBlockSettings.of(Material.WOOL))

    val COOLANT_FLUID_FLOWING = CoolantFluid.Flowing()
    val COOLANT_FLUID_STILL = CoolantFluid.Still()
    val COOLANT_BUCKET = BucketItem(COOLANT_FLUID_STILL, itemSettings())
    val COOLANT = object : FluidBlock(COOLANT_FLUID_STILL, FabricBlockSettings.of(Material.WATER)) {}

    val BUFFER_UPGRADE = IRUpgradeItem(itemSettings().maxCount(1), Upgrade.BUFFER)
    val SPEED_UPGRADE = IRUpgradeItem(itemSettings().maxCount(1), Upgrade.SPEED)
    val ENERGY_UPGRADE = IRUpgradeItem(itemSettings().maxCount(1), Upgrade.ENERGY)
}