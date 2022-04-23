package machucapps.com.presentation.ui.fragments.iss_list

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import machucapps.com.domain.data.PassItem
import machucapps.com.isstracker.databinding.IssTrackItemBinding

class ISSTrackerAdapter(
    private val context: Context
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
                binding.textIssItemDate.text = this.riseTime.toString()
                binding.textIssItemDuration.text = this.duration.toString()
            }
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