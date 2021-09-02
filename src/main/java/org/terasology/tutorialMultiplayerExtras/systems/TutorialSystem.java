/*
 * Copyright 2018 MovingBlocks
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.terasology.tutorialMultiplayerExtras.systems;

import org.terasology.engine.entitySystem.entity.EntityRef;
import org.terasology.engine.entitySystem.event.ReceiveEvent;
import org.terasology.engine.entitySystem.systems.BaseComponentSystem;
import org.terasology.engine.entitySystem.systems.RegisterMode;
import org.terasology.engine.entitySystem.systems.RegisterSystem;
import org.terasology.engine.logic.common.ActivateEvent;
import org.terasology.engine.logic.console.Console;
import org.terasology.engine.registry.In;
import org.terasology.tutorialMultiplayerExtras.components.NetComponent;
import org.terasology.tutorialMultiplayerExtras.events.ConsoleEvent;

/**
 * Handles events for this tutorial
 */
@RegisterSystem(RegisterMode.AUTHORITY)
public class TutorialSystem extends BaseComponentSystem {

    /**
     * Used to print to the in-game console
     */    
    @In
    private Console console;

    /**
     * Called when the entity is activated (by pressing e), sends a {@link ConsoleEvent}
     * to the entity to print its message.
     *
     * @param event The activate event
     * @param entity The entity receiving the event
     * @param comp The net component
     */
    @ReceiveEvent
    public void onActivate(ActivateEvent event, EntityRef entity, NetComponent comp) {
        entity.send(new ConsoleEvent());
    }

    /**
     * Called when an entity wants to print something to the in-game console, prints
     * its stored message.
     *
     * @param event The console event
     * @param entity The entity receiving the event
     * @param comp The net component, from which the message is taken
     */
    @ReceiveEvent
    public void onConsole(ConsoleEvent event, EntityRef entity, NetComponent comp) {
        console.addMessage(comp.message);
    }
}
