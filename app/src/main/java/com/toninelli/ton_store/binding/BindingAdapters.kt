package com.toninelli.ton_store.binding

import android.text.Html
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.databinding.BindingAdapter
import androidx.databinding.BindingConversion
import androidx.lifecycle.MutableLiveData
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButtonToggleGroup
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout
import com.toninelli.ton_store.vo.Resource
import com.toninelli.ton_store.vo.Status
import com.toninelli.ton_store.vo.case
import kotlinx.coroutines.flow.Flow

@BindingAdapter("showHide")
fun showHide(view: View, b: Boolean) {
    view.visibility = if (b) View.VISIBLE else View.GONE
}

@BindingAdapter("showError")
fun <T> showError(view: TextView, resource: Resource<T>?) {
    resource?.case(error = {
        view.text = it.exception.message
    })
}

@BindingAdapter("showError")
fun <T> showError(view: ViewGroup, resource: Resource<T>?) {
    resource?.case(error = {
        Snackbar.make(view, it.exception.message.toString(), Snackbar.LENGTH_SHORT).show()
    })
}

@BindingAdapter("showError")
fun <T> showError(view: TextInputLayout, resource: Resource<T>?) {
    resource?.case(
        error = {
            view.error = it.exception.localizedMessage
        },
        success = {
            view.error = null
        }

    )
}

@BindingConversion
fun convertLoadStateToStatus(states: CombinedLoadStates?): Status? {
    val isError = states?.refresh is LoadState.Error
    val isLoading = states?.refresh is LoadState.Loading
    return when {
        isError -> Status.ERROR
        isLoading -> Status.LOADING
        else -> Status.SUCCESS
    }

}


@Suppress("UNCHECKED_CAST")
@BindingAdapter("adapterResourceData")
fun <T> bindAdapterData(view: RecyclerView, resource: Resource<T>?) {
    resource?.let {
        if (view.adapter is BindableListAdapterData<*>) {
            (view.adapter as BindableListAdapterData<T>).setData(it)
        }
    }
}

@Suppress("UNCHECKED_CAST")
@BindingAdapter("adapterResourceInfo")
fun <T> bindAdapterInfo(view: RecyclerView, info: T?) {

    info?.let {
        if (view.adapter is BindableListAdapterInformation<*>) {
            (view.adapter as BindableListAdapterInformation<T>).setInformation(it)
        }
    }
}

@BindingAdapter("uiListener", "resource")
fun <T> onResourceListener(view: View, listener: ResourceListener, resource: Resource<T>?) {
    resource?.case(
        success = { listener.onResourceSuccess(resource) },
        error = { listener.onResourceError(it.exception) },
        loading = { listener.onResourceLoading() })
}

//@BindingAdapter("namePhoto")
//fun loaderPhotoAdapter(view: ImageView, name: String?) {
//    name?.let {
//        val file =
//            File(ContextWrapper(view.context).getDir("images", Context.MODE_PRIVATE), "${it.substringAfter("/")}.jpg")
//        if (file.exists())
//            Glide.with(view.context!!).load(file.toUri()).into(view)
//        else
//            Glide.with(view.context).load(R.drawable.no_image_placeholder).into(view)
//
//    }
//}

@BindingAdapter("activeLiveData")
fun toggleAdapter(
    view: MaterialButtonToggleGroup,
    active: MutableLiveData<Boolean>?
) {

    view.addOnButtonCheckedListener { _, _, _ ->
        if (view.checkedButtonIds.size == 2) {
            val toggle1 = view.checkedButtonIds[0]
            val toggle2 = view.checkedButtonIds[1]
            if (view.checkedButtonId == toggle1) {
                active?.postValue(true)
            } else {
                active?.postValue(false)
            }
        }
    }

}

//@BindingAdapter("checked")
//fun checkedButton(view: MaterialButton, boolean: Boolean) {
//    view.isChecked = boolean
//}
//
//@InverseBindingAdapter(attribute = "checked")
//fun getIsActiveFromView(view: MaterialButton): Boolean {
//    return view.isChecked
//}
//
//@BindingAdapter("isCheckedAttrChanged")
//fun isActivatedAttrChanged(view: MaterialButton, listener: InverseBindingListener?) {
//    view.setOnClickListener {
//        logd("changed  ${view.id}  ${view.isChecked}")
//        listener?.onChange()
//    }
//}

//@BindingAdapter("loadImage")
//fun loadImage(view: ImageView, url: String?) {
//    url?.let {
//        Picasso.get().load(url).placeholder(R.drawable.bubu1).into(view)
//    }
//}

@BindingAdapter("setTextHTML")
fun setTextHTML(view: TextView, s: String) {
    val text = Html.fromHtml(s)
    view.text = text
}


//@BindingAdapter("popUpMenu")
//fun showPopUpMenu(view: MaterialButton, list: List<String>) {
//
//    val menu = ListPopupWindow(view.context, null, R.attr.listPopupWindowStyle)
//    val adapter = ArrayAdapter<String>(view.context, R.layout.popup_menu_item, list)
//    menu.setAdapter(adapter)
//    menu.anchorView = view
//    menu.setOnItemClickListener { _, _, position, _ ->
//        view.text = adapter.getItem(position).toString()
//        menu.dismiss()
//    }
//    view.setOnClickListener { menu.show() }
//}
//
//




