package sakancom.tenant;

import io.cucumber.core.logging.Logger;
import io.cucumber.core.logging.LoggerFactory;

import java.sql.ResultSet;

public class AdvertisementHousePrinter {
    public AdvertisementHousePrinter(StringBuilder advertisementStringBuilder) {
        this.advertisementStringBuilder = advertisementStringBuilder;
    }

    private final StringBuilder advertisementStringBuilder;
    private static final Logger logger=  LoggerFactory.getLogger(AdvertisementHousePrinter.class);
    public  void printAvailableHouse(ResultSet r){
        String  advertisement ;
        try {
                while(r.next()){
                    advertisement =
                     ("\n")
                    +("╔═══════════════════════════════════════════════════════════════╗\n")
                    +("║                                                               ║\n")
                    +("║           HOUSE FOR RENT - SakanCom                           ║\n")
                    +("║                                                               ║\n")
                    +("╟───────────────────────────────────────────────────────────────╢\n")
                    +("║                                                               ║\n")
                    +("║   AVAILABLE NOW - MOVE-IN READY!                              ║\n")
                    +("║   "+r.getString(3)+" House for: "+r.getString(8)+"                            ║\n")
                    +("║   This beautiful "+r.getInt(10)+"-bedroom                                    ║\n")
                    +("║   perfect for families and professionals.                     ║\n")
                    +("║   Located in a safe and quiet neighborhood                    ║\n")
                    +("║                                                               ║\n")
                    +("╟───────────────────────────────────────────────────────────────╢\n")
                    +("║                                                               ║\n")
                    +("║   FEATURES:                                                   ║\n")
                    +("║   - Area About :"+r.getString(12)+"                                           ║\n")
                    +("║   - "+r.getInt(10)+" spacious bedrooms                                       ║\n")
                    +("║   - "+ r.getString(11)+" bathrooms                                               ║\n")
                    +("║   - Modern kitchen with appliances                            ║\n")
                    +("║   - Cozy living room with fireplace                           ║\n")
                    +("║   - Large backyard with garden                                ║\n")
                    +("║   - "+ r.getString(13)+"                                                   ║\n")
                    +("║   - Pet-friendly                                              ║\n")
                    +("║                                                               ║\n")
                    +("╟───────────────────────────────────────────────────────────────╢\n")
                    +("║                                                               ║\n")
                    +("║   LOCATION:                                                   ║\n")
                    +("║   "+r.getString(4)+","+ r.getString(5)+" street                                         ║\n")
                    +("║   Close to schools, parks, and shops                          ║\n")
                    +("║   Easy access to public transportation                        ║\n")
                    +("║                                                               ║\n")
                    +("╟───────────────────────────────────────────────────────────────╢\n")
                    +("║                                                               ║\n")
                    +("║   RENT: $"+(r.getInt(9))+"/month                                           ║\n")
                    +("║   Utilities not included                                      ║\n")
                    +("║   For inquiries and viewings                                  ║\n")
                    +("║   please call: "+r.getString(7)+"                                       ║\n")
                    +("║   Image :"+r.getString(14)+"          ║\n")
                    +("║   Advertisement Number : "+r.getString(1)+"                                    ║\n")
                    +("║                                                               ║\n")
                    +("╚═══════════════════════════════════════════════════════════════╝\n\n\n");

                    advertisementStringBuilder.append(advertisement);
                }

                logger.info(advertisementStringBuilder::toString);
                advertisementStringBuilder.setLength(0);
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }

    }


