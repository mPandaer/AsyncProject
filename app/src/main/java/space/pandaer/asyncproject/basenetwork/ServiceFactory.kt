package space.pandaer.asyncproject.basenetwork

import retrofit2.Retrofit

object ServiceFactory {

    //创建retrofit的大管家
    private val retrofitBuilder = Retrofit.Builder()

    /**
     * block 一个配置文件配置baseurl 拦截器等等
     */
    fun <T> createService(serviceClass:Class<T>,block : Retrofit.Builder.() -> Unit) : T {
        val retrofit = retrofitBuilder.apply(block).build()
        return retrofit.create(serviceClass)
    }
}