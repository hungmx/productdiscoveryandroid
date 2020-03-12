import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity
data class Extras (

	@field:SerializedName("totalItems") val totalItems : Int,
	@field:SerializedName("page") val page : Int,
	@field:SerializedName("pageSize") val pageSize : Int,
	@field:SerializedName("priceRanges") val priceRanges : List<PriceRanges>
){
	data class PriceRanges (

		@field:SerializedName("maxPrice") val maxPrice : Int,
		@field:SerializedName("minPrice") val minPrice : Double
	)
}