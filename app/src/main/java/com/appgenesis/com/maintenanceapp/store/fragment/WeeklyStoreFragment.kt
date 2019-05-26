import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View
import com.appgenesis.com.maintenanceapp.fragment.StorePreventivemaintenanceParent


class WeeklyStoreFragment : StorePreventivemaintenanceParent() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getStoreRequestData()

    }


    companion object {

        fun newInstance(args: Bundle): WeeklyStoreFragment {
            val fragment = WeeklyStoreFragment()
            fragment.arguments = args
            return fragment
        }
    }
}
