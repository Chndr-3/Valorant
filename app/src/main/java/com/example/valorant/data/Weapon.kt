package com.example.valorant.data

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.RawValue

@Parcelize
data class Weapon(

	@field:SerializedName("data")
	val data: List<WeaponItems>? = null,

	@field:SerializedName("status")
	val status: Int? = null
) : Parcelable

@Parcelize
data class GridPosition(

	@field:SerializedName("column")
	val column: Double? = null,

	@field:SerializedName("row")
	val row: Double? = null
) : Parcelable

@Parcelize
data class WeaponItems(

	@field:SerializedName("skins")
	val skins: List<SkinsItem?>? = null,

	@field:SerializedName("displayIcon")
	val displayIcon: String? = null,

	@field:SerializedName("killStreamIcon")
	val killStreamIcon: String? = null,

	@field:SerializedName("shopData")
	val shopData: ShopData? = null,

	@field:SerializedName("defaultSkinUuid")
	val defaultSkinUuid: String? = null,

	@field:SerializedName("displayName")
	val displayName: String? = null,

	@field:SerializedName("assetPath")
	val assetPath: String? = null,

	@field:SerializedName("weaponStats")
	val weaponStats: WeaponStats? = null,

	@field:SerializedName("category")
	val category: String? = null,

	@field:SerializedName("uuid")
	val uuid: String? = null
) : Parcelable

@Parcelize
data class ShopData(

	@field:SerializedName("canBeTrashed")
	val canBeTrashed: Boolean? = null,

	@field:SerializedName("image")
	val image: @RawValue Any? = null,

	@field:SerializedName("cost")
	val cost: Double? = null,

	@field:SerializedName("newImage")
	val newImage: String? = null,

	@field:SerializedName("newImage2")
	val newImage2: @RawValue Any? = null,

	@field:SerializedName("assetPath")
	val assetPath: String? = null,

	@field:SerializedName("gridPosition")
	val gridPosition: GridPosition? = null,

	@field:SerializedName("category")
	val category: String? = null,

	@field:SerializedName("categoryText")
	val categoryText: String? = null
) : Parcelable

@Parcelize
data class AdsStats(

	@field:SerializedName("fireRate")
	val fireRate: @RawValue Any? = null,

	@field:SerializedName("burstCount")
	val burstCount: Double? = null,

	@field:SerializedName("runSpeedMultiplier")
	val runSpeedMultiplier: @RawValue Any? = null,

	@field:SerializedName("zoomMultiplier")
	val zoomMultiplier: @RawValue Any? = null,

	@field:SerializedName("firstBulletAccuracy")
	val firstBulletAccuracy: @RawValue Any? = null
) : Parcelable

@Parcelize
data class AirBurstStats(

	@field:SerializedName("shotgunPelletCount")
	val shotgunPelletCount: Double? = null,

	@field:SerializedName("burstDistance")
	val burstDistance:@RawValue Any? = null
) : Parcelable

@Parcelize
data class LevelsItem(

	@field:SerializedName("displayIcon")
	val displayIcon: String? = null,

	@field:SerializedName("levelItem")
	val levelItem:@RawValue Any? = null,

	@field:SerializedName("displayName")
	val displayName: String? = null,

	@field:SerializedName("assetPath")
	val assetPath: String? = null,

	@field:SerializedName("uuid")
	val uuid: String? = null,

	@field:SerializedName("streamedVideo")
	val streamedVideo:@RawValue Any? = null
) : Parcelable

@Parcelize
data class ChromasItem(

	@field:SerializedName("displayIcon")
	val displayIcon:@RawValue Any? = null,

	@field:SerializedName("swatch")
	val swatch:@RawValue Any? = null,

	@field:SerializedName("displayName")
	val displayName: String? = null,

	@field:SerializedName("assetPath")
	val assetPath: String? = null,

	@field:SerializedName("fullRender")
	val fullRender: String? = null,

	@field:SerializedName("uuid")
	val uuid: String? = null,

	@field:SerializedName("streamedVideo")
	val streamedVideo:@RawValue Any? = null
) : Parcelable

@Parcelize
data class WeaponStats(

	@field:SerializedName("damageRanges")
	val damageRanges: List<DamageRangesItem?>? = null,

	@field:SerializedName("equipTimeSeconds")
	val equipTimeSeconds:@RawValue Any? = null,

	@field:SerializedName("shotgunPelletCount")
	val shotgunPelletCount: Double? = null,

	@field:SerializedName("adsStats")
	val adsStats: AdsStats? = null,

	@field:SerializedName("fireRate")
	val fireRate: Double? = null,

	@field:SerializedName("runSpeedMultiplier")
	val runSpeedMultiplier:@RawValue Any? = null,

	@field:SerializedName("feature")
	val feature: String? = null,

	@field:SerializedName("airBurstStats")
	val airBurstStats:@RawValue Any? = null,

	@field:SerializedName("reloadTimeSeconds")
	val reloadTimeSeconds: Double? = null,

	@field:SerializedName("wallPenetration")
	val wallPenetration: String? = null,

	@field:SerializedName("magazineSize")
	val magazineSize: Double? = null,

	@field:SerializedName("fireMode")
	val fireMode:@RawValue Any? = null,

	@field:SerializedName("firstBulletAccuracy")
	val firstBulletAccuracy:@RawValue Any? = null,

	@field:SerializedName("altFireType")
	val altFireType: String? = null,

	@field:SerializedName("altShotgunStats")
	val altShotgunStats:@RawValue Any? = null
) : Parcelable

@Parcelize
data class DamageRangesItem(

	@field:SerializedName("rangeEndMeters")
	val rangeEndMeters: Double? = null,

	@field:SerializedName("headDamage")
	val headDamage: Double? = null,

	@field:SerializedName("bodyDamage")
	val bodyDamage: Double? = null,

	@field:SerializedName("legDamage")
	val legDamage:@RawValue Any? = null,

	@field:SerializedName("rangeStartMeters")
	val rangeStartMeters: Double? = null
) : Parcelable

@Parcelize
data class AltShotgunStats(

	@field:SerializedName("shotgunPelletCount")
	val shotgunPelletCount: Double? = null,

	@field:SerializedName("burstRate")
	val burstRate:@RawValue Any? = null
) : Parcelable

@Parcelize
data class SkinsItem(

	@field:SerializedName("displayIcon")
	val displayIcon: String? = null,

	@field:SerializedName("contentTierUuid")
	val contentTierUuid: String? = null,

	@field:SerializedName("wallpaper")
	val wallpaper:@RawValue Any? = null,

	@field:SerializedName("displayName")
	val displayName: String? = null,

	@field:SerializedName("assetPath")
	val assetPath: String? = null,

	@field:SerializedName("chromas")
	val chromas: List<ChromasItem?>? = null,

	@field:SerializedName("uuid")
	val uuid: String? = null,

	@field:SerializedName("themeUuid")
	val themeUuid: String? = null,

	@field:SerializedName("levels")
	val levels: List<LevelsItem?>? = null
) : Parcelable
