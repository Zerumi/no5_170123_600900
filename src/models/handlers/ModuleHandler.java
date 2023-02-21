package models.handlers;

import exceptions.BuildObjectException;

/**
 * Base interface for ModelHandler. Uses for building objects.
 *
 * @param <T> Type of building module.
 *
 * @since 1.0
 * @author zerumi
 */
public interface ModuleHandler<T> {

    /**
     * Provides method to generate objects.
     *
     * @return Created object.
     */
    T buildObject() throws BuildObjectException;
}
