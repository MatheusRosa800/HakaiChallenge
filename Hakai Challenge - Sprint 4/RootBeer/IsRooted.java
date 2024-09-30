import android.content.Context;
import com.scottyab.rootbeer.RootBeer;

public class isRooted {

    
    public boolean isDeviceRooted(Context context) {
        RootBeer rootBeer = new RootBeer(context);
        return rootBeer.isRooted();
    }
}
