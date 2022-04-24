package machucapps.com.presentation.ui.fragments.iss_list

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import machucapps.com.domain.data.PassItem
import machucapps.com.isstracker.R
import machucapps.com.isstracker.databinding.IssTrackItemBinding
import machucapps.com.presentation.ui.ext.formatDate
import machucapps.com.presentation.ui.ext.formatDuration

class ISSTrackerAdapter(
    private val context: Context,
    private val listener: (PassItem) -> Unit
) : RecyclerView.Adapter<ISSTrackerAdapter.ISSTrackerViewHolder>() {

    private val passesList = mutableListOf<PassItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ISSTrackerViewHolder {
        val binding = IssTrackItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ISSTrackerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ISSTrackerViewHolder, position: Int) {
        with(holder) {
            with(passesList[position]) {
                binding.textIssItemDate.text = this.riseTime.formatDate()
                binding.textIssItemDuration.text =
                    this.duration.formatDuration(context.getString(R.string.iss_duration_time))
            }

            itemView.setOnClickListener { listener(passesList[position]) }
        }
    }

    override fun getItemCount(): Int = passesList.size

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getItemViewType(position: Int): Int = position

    fun setData(newPasses: List<PassItem>) {
        passesList.clear()
        passesList.addAll(newPasses)
    }

    inner class ISSTrackerViewHolder(val binding: IssTrackItemBinding) :
        RecyclerView.ViewHolder(binding.root)
}