package models.validators;

import models.Coordinates;
import models.Location;
import models.Route;

import java.util.Optional;

/**
 * Implementation of validator for name field. (Route)
 *  *
 *  * @since 1.1
 *  * @author Zerumi
 */
public class RouteValidator implements Validator<Route> {
    @Override
    public boolean validate(Route route) {
        Validator<Long> idValidate = (id) -> id != null && id > 0;

        return idValidate.validate(route.getId())
                && new NameValidator().validate(route.getName())
                && new DistanceValidator().validate(Optional.of(route).map(Route::getDistance).orElse(0))
                && new CoordXValidator().validate(Optional.of(route).map(Route::getCoordinates).map(Coordinates::getX).orElse(0d))
                && new CoordYValidator().validate(Optional.of(route).map(Route::getCoordinates).map(Coordinates::getY).orElse(null))
                && new LocationYZValidator().validate(Optional.of(route).map(Route::getFrom).map(Location::getY).orElse(0L))
                && new LocationYZValidator().validate(Optional.of(route).map(Route::getFrom).map(Location::getZ).orElse(0L))
                && new LocationYZValidator().validate(Optional.of(route).map(Route::getTo).map(Location::getY).orElse(0L))
                && new LocationYZValidator().validate(Optional.of(route).map(Route::getTo).map(Location::getZ).orElse(0L))
                && new LocationNameValidator().validate(Optional.of(route).map(Route::getFrom).map(Location::getName).orElse(null))
                && new LocationNameValidator().validate(Optional.of(route).map(Route::getTo).map(Location::getName).orElse(null));
    }
}
