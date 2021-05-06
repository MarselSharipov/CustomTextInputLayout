/*
 * Copyright 2018 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ru.unluckybatman.material.shape;

import androidx.annotation.NonNull;

/**
 * Utility methods for {@link MaterialShapeDrawable} and related classes.
 */
public class MaterialShapeUtils {

    private MaterialShapeUtils() {
    }

    @NonNull
    static CornerTreatment createCornerTreatment(@CornerFamily int cornerFamily) {
        switch (cornerFamily) {
            case CornerFamily.ROUNDED:
                return new RoundedCornerTreatment();
            case CornerFamily.CUT:
                return new CutCornerTreatment();
            default:
                return createDefaultCornerTreatment();
        }
    }

    @NonNull
    static CornerTreatment createDefaultCornerTreatment() {
        return new RoundedCornerTreatment();
    }

    @NonNull
    static EdgeTreatment createDefaultEdgeTreatment() {
        return new EdgeTreatment();
    }

}
