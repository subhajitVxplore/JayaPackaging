package com.jaya.app.packaging.repositoryImpls


import com.jaya.app.core.common.Resource
import com.jaya.app.core.domain.models.AddProductModel
import com.jaya.app.core.domain.models.AppVersionModel
import com.jaya.app.core.domain.models.BaseUrlModel
import com.jaya.app.core.domain.models.GetOtpModel
import com.jaya.app.core.domain.models.ProductTypesModel
import com.jaya.app.core.domain.models.RecentPackagingModel
import com.jaya.app.core.domain.models.UserDetailsModel
import com.jaya.app.core.domain.repositories.AddProductRepository
import com.jaya.app.core.domain.repositories.DashboardRepository
import com.jaya.app.core.domain.repositories.LoginRepository
import com.jaya.app.core.domain.repositories.SplashRepository
import com.jaya.app.packaging.module.MyApiList
import okhttp3.MultipartBody
import javax.inject.Inject

class AddProductRepositoryImpl @Inject constructor(
    private val myApiList: MyApiList
) : AddProductRepository {

    override suspend fun getProductTypes(): Resource<ProductTypesModel> {
        return try {
            Resource.Success(myApiList.getProductTypes())
        } catch (ex: Exception) {
            Resource.Error(message = ex.message)
        }
    }

    override suspend fun addProduct(): Resource<AddProductModel> {
        return try {
            Resource.Success(myApiList.addProduct())
        } catch (ex: Exception) {
            Resource.Error(message = ex.message)
        }
    }

    override suspend fun submitPackingDetails(
        user_id: String,
        product_name: String,
        packing_name: String,
        batch_number: String,
        product_type: String,
        start_time: String,
        end_time: String,
        video_clip_list: List<MultipartBody.Part>
    ): Resource<AddProductModel> {
        return try {
            Resource.Success(myApiList.submitPackingDetails(user_id,product_name,packing_name,batch_number,product_type,start_time,end_time,video_clip_list))
        } catch (ex: Exception) {
            Resource.Error(message = ex.message)
        }
    }


}

