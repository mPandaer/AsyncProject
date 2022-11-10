package space.pandaer.asyncproject.basenetwork


//网络状态
sealed interface DataState{
    object Waiting : DataState //等待数据
    object Loading : DataState //正在加载数据
    class Success<T>(val data:T): DataState //数据加载成功 data : 从网络获取的数据
    class Failure(val msg:String) : DataState // 数据加载失败 msg : 失败的原因
}