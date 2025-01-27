package com.myjar.jarassignment.data.remote.model
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "computer_item")
data class ComputerItem(
    @PrimaryKey val id: String,
    val name: String,
    @Embedded val data: ItemData? = null
)

data class ItemData(
    val color: String? = null,
    val capacity: String? = null,
    @SerializedName("price") val price: Double? = null,
    @SerializedName("capacity GB") val capacityGB: Int? = null,
    @SerializedName("Screen size") val screenSize: Double? = null,
    @SerializedName("Description") val description: String? = null,
    @SerializedName("Generation") val generation: String? = null,
    @SerializedName("Strap Colour") val strapColour: String? = null,
    @SerializedName("Case Size") val caseSize: String? = null,
    @SerializedName("CPU model") val cpuModel: String? = null,
    @SerializedName("Hard disk size") val hardDiskSize: String? = null
)
