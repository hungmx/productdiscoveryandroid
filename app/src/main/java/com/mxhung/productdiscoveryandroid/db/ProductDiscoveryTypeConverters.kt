package com.mxhung.productdiscoveryandroid.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mxhung.productdiscoveryandroid.model.Extra
import com.mxhung.productdiscoveryandroid.model.Products
import com.mxhung.productdiscoveryandroid.model.Result
import java.lang.reflect.Type


class ProductDiscoveryTypeConverters {
    @TypeConverter
    fun stringToIntList(data: String?): List<Int>? {
        return data?.let {
            it.split(",").map {
                try {
                    it.toInt()
                } catch (ex: NumberFormatException) {
//                    Timber.e(ex, "Cannot convert $it to number")
                    null
                }
            }
        }?.filterNotNull()
    }

    @TypeConverter
    fun intListToString(ints: List<Int>?): String? {
        return ints?.joinToString(",")
    }
    @TypeConverter
    fun stringToStringList(data: String?): List<String>? {
        return data?.let { it ->
            it.split(",").map {
                try {
                    it.toString()
                } catch (ex: NumberFormatException) {
//                    Timber.e(ex, "Cannot convert $it to number")
                    null
                }
            }
        }?.filterNotNull()
    }

    @TypeConverter
    fun stringListToString(strings: List<String>?): String? {
        return strings?.joinToString(",")
    }

    @TypeConverter
    fun productLineToString(app: Products.ProductLine): String = Gson().toJson(app)

    @TypeConverter
    fun stringToProductLine(string: String): Products.ProductLine = Gson().fromJson(string, Products.ProductLine::class.java)

    @TypeConverter
    fun priceToString(app: Products.Price): String = Gson().toJson(app)

    @TypeConverter
    fun stringToPrice(string: String): Products.Price = Gson().fromJson(string, Products.Price::class.java)

    @TypeConverter
    fun colorToString(app: Products.Color): String = Gson().toJson(app)

    @TypeConverter
    fun stringToColor(string: String): Products.Color = Gson().fromJson(string, Products.Color::class.java)

    @TypeConverter
    fun attributeSetToString(app: Products.AttributeSet): String = Gson().toJson(app)

    @TypeConverter
    fun stringToAttributeSet(string: String): Products.AttributeSet = Gson().fromJson(string, Products.AttributeSet::class.java)

    @TypeConverter
    fun resultToString(app: Result): String = Gson().toJson(app)

    @TypeConverter
    fun stringToResult(string: String): Result = Gson().fromJson(string, Result::class.java)

    @TypeConverter
    fun extraToString(app: Extra): String = Gson().toJson(app)

    @TypeConverter
    fun stringToExtra(string: String): Extra = Gson().fromJson(string, Extra::class.java)
    @TypeConverter // note this annotation
    fun fromPromotionPricesList(optionValues: List<Products.PromotionPrices>?): String? {
        if (optionValues == null) {
            return null
        }
        val gson = Gson()
        val type: Type = object :
            TypeToken<List<Products.PromotionPrices>?>() {}.type
        return gson.toJson(optionValues, type)
    }

    @TypeConverter // note this annotation
    fun toPromotionPricesList(optionValuesString: String?): List<Products.PromotionPrices>? {
        if (optionValuesString == null) {
            return null
        }
        val gson = Gson()
        val type: Type = object :
            TypeToken<List<Products.PromotionPrices>?>() {}.type
        return gson.fromJson(optionValuesString, type)
    }

    @TypeConverter // note this annotation
    fun fromCategoriesList(optionValues: List<Products.Categories>?): String? {
        if (optionValues == null) {
            return null
        }
        val gson = Gson()
        val type: Type = object :
            TypeToken<List<Products.Categories>?>() {}.type
        return gson.toJson(optionValues, type)
    }

    @TypeConverter // note this annotation
    fun toCategoriesList(optionValuesString: String?): List<Products.Categories>? {
        if (optionValuesString == null) {
            return null
        }
        val gson = Gson()
        val type: Type = object :
            TypeToken<List<Products.Categories>?>() {}.type
        return gson.fromJson(optionValuesString, type)
    }

    @TypeConverter // note this annotation
    fun fromPriceRangesList(optionValues: List<Extra.PriceRanges>?): String? {
        if (optionValues == null) {
            return null
        }
        val gson = Gson()
        val type: Type = object :
            TypeToken<List<Extra.PriceRanges>?>() {}.type
        return gson.toJson(optionValues, type)
    }

    @TypeConverter // note this annotation
    fun toPriceRangesList(optionValuesString: String?): List<Extra.PriceRanges>? {
        if (optionValuesString == null) {
            return null
        }
        val gson = Gson()
        val type: Type = object :
            TypeToken<List<Extra.PriceRanges>?>() {}.type
        return gson.fromJson(optionValuesString, type)
    }
    @TypeConverter // note this annotation
    fun fromProductsList(optionValues: List<Products>?): String? {
        if (optionValues == null) {
            return null
        }
        val gson = Gson()
        val type: Type = object :
            TypeToken<List<Products>?>() {}.type
        return gson.toJson(optionValues, type)
    }

    @TypeConverter // note this annotation
    fun toProductsList(optionValuesString: String?): List<Products>? {
        if (optionValuesString == null) {
            return null
        }
        val gson = Gson()
        val type: Type = object :
            TypeToken<List<Products>?>() {}.type
        return gson.fromJson(optionValuesString, type)
    }
    @TypeConverter // note this annotation
    fun fromTotalAvailableByStocksList(optionValues: List<Products.TotalAvailableByStocks>?): String? {
        if (optionValues == null) {
            return null
        }
        val gson = Gson()
        val type: Type = object :
            TypeToken<List<Products.TotalAvailableByStocks>?>() {}.type
        return gson.toJson(optionValues, type)
    }

    @TypeConverter // note this annotation
    fun toTotalAvailableByStocksList(optionValuesString: String?): List<Products.TotalAvailableByStocks>? {
        if (optionValuesString == null) {
            return null
        }
        val gson = Gson()
        val type: Type = object :
            TypeToken<List<Products.TotalAvailableByStocks>?>() {}.type
        return gson.fromJson(optionValuesString, type)
    }
    @TypeConverter // note this annotation
    fun fromImagesList(optionValues: List<Products.Images>?): String? {
        if (optionValues == null) {
            return null
        }
        val gson = Gson()
        val type: Type = object :
            TypeToken<List<Products.Images>?>() {}.type
        return gson.toJson(optionValues, type)
    }

    @TypeConverter // note this annotation
    fun toImagesList(optionValuesString: String?): List<Products.Images>? {
        if (optionValuesString == null) {
            return null
        }
        val gson = Gson()
        val type: Type = object :
            TypeToken<List<Products.Images>?>() {}.type
        return gson.fromJson(optionValuesString, type)
    }
}
