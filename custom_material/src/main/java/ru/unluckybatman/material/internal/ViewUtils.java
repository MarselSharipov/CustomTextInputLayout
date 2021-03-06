/*
 * Copyright (C) 2015 The Android Open Source Project
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

package ru.unluckybatman.material.internal;

import android.view.View;
import android.view.ViewParent;

import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.core.view.ViewCompat;

import static androidx.annotation.RestrictTo.Scope.LIBRARY_GROUP;

/**
 * Utils class for custom views.
 */
@RestrictTo(LIBRARY_GROUP)
public class ViewUtils {

    private ViewUtils() {
    }

    /**
     * Returns the absolute elevation of the parent of the provided {@code view}, or in other words,
     * the sum of the elevations of all ancestors of the {@code view}.
     */
    public static float getParentAbsoluteElevation(@NonNull View view) {
        float absoluteElevation = 0;
        ViewParent viewParent = view.getParent();
        while (viewParent instanceof View) {
            absoluteElevation += ViewCompat.getElevation((View) viewParent);
            viewParent = viewParent.getParent();
        }
        return absoluteElevation;
    }

}
