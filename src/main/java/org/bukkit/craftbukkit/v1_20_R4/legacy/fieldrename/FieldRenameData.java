package org.bukkit.craftbukkit.v1_20_R4.legacy.fieldrename;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;
import org.bukkit.craftbukkit.v1_20_R4.util.ApiVersion;

public record FieldRenameData(NavigableMap<ApiVersion, Map<String, String>> versionData, Map<String, String> data) {

    public String getReplacement(ApiVersion apiVersion, String from) {
        if (from == null) {
            return null;
        }

        from = from.toUpperCase(Locale.ROOT);
        from = this.data.getOrDefault(from, from);

        for (Map.Entry<ApiVersion, Map<String, String>> entry : this.versionData.entrySet()) {
            if (apiVersion.isNewerThanOrSameAs(entry.getKey())) {
                continue;
            }

            from = entry.getValue().getOrDefault(from, from);
        }

        return from;
    }

    public static class Builder {

        private final Map<String, String> data = new HashMap<>();
        private final NavigableMap<ApiVersion, Map<String, String>> versionData = new TreeMap<>();
        private ApiVersion currentVersion;

        public static Builder newBuilder() {
            return new Builder();
        }

        public Builder forVersionsBefore(ApiVersion apiVersion) {
            this.currentVersion = apiVersion;
            return this;
        }

        public Builder forAllVersions() {
            this.currentVersion = null;
            return this;
        }

        public Builder change(String from, String to) {
            if (this.currentVersion != null) {
                this.versionData.computeIfAbsent(this.currentVersion, d -> new HashMap<>()).put(from, to);
            } else {
                this.data.put(from, to);
            }
            return this;
        }

        public FieldRenameData build() {
            return new FieldRenameData(this.versionData, this.data);
        }
    }
}
