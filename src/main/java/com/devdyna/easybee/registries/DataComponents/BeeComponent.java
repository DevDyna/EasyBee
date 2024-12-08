// package com.devdyna.easybee.registries.DataComponents;

// import java.util.function.UnaryOperator;

// import com.devdyna.easybee.EasyBee;

// import net.minecraft.core.UUIDUtil;
// import net.minecraft.core.component.DataComponentType;
// import net.neoforged.bus.api.IEventBus;
// import net.neoforged.neoforge.registries.DeferredHolder;
// import net.neoforged.neoforge.registries.DeferredRegister;

// public class BeeComponent {
    
//     @SuppressWarnings("removal")
//     public static final DeferredRegister<DataComponentType<?>> DATA_COMPONENT_TYPES =
//             DeferredRegister.createDataComponents(EasyBee.MODID);
//     public static final DeferredHolder<DataComponentType<?>, DataComponentType<UUIDUtil>> BEE_ENTITY_DATA = register("bee_entity_data",
//             builder -> builder.cacheEncoding());
            
//     private static <T>DeferredHolder<DataComponentType<?>, DataComponentType<T>> register(String name,
//                                                                                           UnaryOperator<DataComponentType.Builder<T>> builderOperator) {
//         return DATA_COMPONENT_TYPES.register(name, () -> builderOperator.apply(DataComponentType.builder()).build());
//     }
//     public static void register(IEventBus eventBus) {
//         DATA_COMPONENT_TYPES.register(eventBus);
//     }

// }
