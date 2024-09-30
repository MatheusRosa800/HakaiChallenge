import android.content.Context;
import com.scottyab.rootbeer.RootBeer;

public class isRooted {

    // Function to check if the device is rooted
    public boolean isDeviceRooted(Context context) {
        RootBeer rootBeer = new RootBeer(context);
        return rootBeer.isRooted();
    }
}
