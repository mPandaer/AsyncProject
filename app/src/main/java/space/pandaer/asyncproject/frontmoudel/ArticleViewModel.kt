package space.pandaer.asyncproject.frontmoudel

import android.widget.Toast
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import space.pandaer.asyncproject.basenetwork.DataState
import space.pandaer.asyncproject.frontmoudel.model.Article
import space.pandaer.asyncproject.frontmoudel.repository.ArticleRepository


//界面和业务逻辑之间通信
class ArticleViewModel : ViewModel() {

    //文章仓库
    private val repository = ArticleRepository


    //文章列表数据流
    private val _allArticleState: MutableStateFlow<DataState> = MutableStateFlow(DataState.Waiting)
    val allArticleState : StateFlow<DataState>
        get() = _allArticleState

    //文章详情数据流
    private val _detailArticleState: MutableStateFlow<DataState> = MutableStateFlow(DataState.Waiting)
    val detailArticleState : StateFlow<DataState>
        get() = _detailArticleState

    //新建文章数据流
    private val _createArticleState: MutableStateFlow<DataState> = MutableStateFlow(DataState.Waiting)
    val createArticleState : StateFlow<DataState>
        get() = _createArticleState




    //失败后重新加载 //todo(有待优化)
    fun reLoadingInList() {
        _allArticleState.value = DataState.Waiting
    }
    fun reLoadingInDetail() {
        _detailArticleState.value = DataState.Waiting
    }
    fun reLoadingInCreate() {
        _createArticleState.value = DataState.Waiting
    }

    //获得全部文章
    fun getAllArticles() {
        _allArticleState.value = DataState.Loading
        viewModelScope.launch {
            delay(500)
            val state = repository.getAllArticle()
            if (state is DataState.Failure) {
                _allArticleState.value = state
            }else{
                _allArticleState.value = state
            }
        }
    }

    //获取单篇详细文章
    fun getSingleArticle(id : Int) {
        _detailArticleState.value = DataState.Loading
        viewModelScope.launch {
            delay(500)
            val state = repository.getSingleArticle(id)
            if (state is DataState.Failure) {
                _detailArticleState.value = state
            }else{
                _detailArticleState.value = state
            }
        }
    }

    //创建新的文章
    fun createNewArticle(newArticle:Article) {
        _createArticleState.value = DataState.Loading
        viewModelScope.launch {
            val status = repository.createNewArticle(newArticle)
            if (status.data){
                _createArticleState.value = DataState.Success(status)
            }else{
                _createArticleState.value = DataState.Failure("请求失败：${status.msg}")
            }

        }
    }




}