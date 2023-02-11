package models.handlers;

/**
 * Base interface for ModelHandler. Uses for building objects.
 *
 * @param <T> Type of building module.
 */
public interface ModuleHandler<T> {

    /**
     * Provides method to generate objects by CLI.
     *
     * @return Created object.
     */
    T buildObject();
}
