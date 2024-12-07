package net.neoforged.neodev;

import net.neoforged.neodev.utils.FileUtils;
import org.gradle.api.file.ConfigurableFileCollection;
import org.gradle.api.file.RegularFileProperty;
import org.gradle.api.provider.Property;
import org.gradle.api.tasks.Classpath;
import org.gradle.api.tasks.Input;
import org.gradle.api.tasks.InputFile;
import org.gradle.api.tasks.InputFiles;
import org.gradle.api.tasks.Internal;
import org.gradle.api.tasks.JavaExec;
import org.gradle.api.tasks.OutputFile;
import org.gradle.api.tasks.TaskAction;

import javax.inject.Inject;
import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Runs <a href="https://github.com/neoforged/JavaSourceTransformer">JavaSourceTransformer</a> to apply
 * access transformers to the Minecraft source code for extending the access level of existing classes/methods/etc.
 * <p>
 * Note that at runtime, FML also applies access transformers.
 */
abstract class ApplyAccessTransformer extends JavaExec {
    @InputFile
    public abstract RegularFileProperty getInputJar();

    @InputFiles
    public abstract ConfigurableFileCollection getAccessTransformers();

    @Input
    public abstract Property<Boolean> getValidate();

    @OutputFile
    public abstract RegularFileProperty getOutputJar();

    // Used to give JST more information about the classes.
    @Classpath
    public abstract ConfigurableFileCollection getLibraries();

    @Internal
    public abstract RegularFileProperty getLibrariesFile();

    @Inject
    public ApplyAccessTransformer() {}

    @Override
    @TaskAction
    public void exec() {
        try {
            FileUtils.writeLinesSafe(
                    getLibrariesFile().getAsFile().get().toPath(),
                    getLibraries().getFiles().stream().map(File::getAbsolutePath).toList(),
                    StandardCharsets.UTF_8);
        } catch (IOException exception) {
            throw new UncheckedIOException("Failed to write libraries for JST.", exception);
        }

        var args = new ArrayList<>(Arrays.asList(
                "--enable-accesstransformers",
                "--access-transformer-validation", getValidate().get() ? "error" : "log",
                "--libraries-list", getLibrariesFile().getAsFile().get().getAbsolutePath()
        ));

        for (var file : getAccessTransformers().getFiles()) {
            args.addAll(Arrays.asList(
                    "--access-transformer", file.getAbsolutePath()
            ));
        }

        args.addAll(Arrays.asList(
                getInputJar().getAsFile().get().getAbsolutePath(),
                getOutputJar().getAsFile().get().getAbsolutePath()));

        args(args);

        super.exec();
    }
}
