package com.tirmizee

import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import java.io.File
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.util.*

@SpringBootApplication
class SpringBootK8sVolumePvcApplication: CommandLineRunner {

	override fun run(vararg args: String?) {
		val path: Path = Paths.get("/data/${UUID.randomUUID()}.txt")
		runCatching {
			Files.createFile(path)
		}.onSuccess {
			println("File ${path.fileName} created.")
		}.onFailure {
			println("File ${path.fileName} already exists.")
		}

		var pathnames: Array<String>
		val f = File("/data")
		f.runCatching {

			pathnames = list()

			for (pathname in pathnames) {
				println(pathname)
			}

		}.onSuccess {
			println("Read file success.")
		}.onFailure {
			println("Read file failure.")
		}

	}

}

fun main(args: Array<String>) {
	runApplication<SpringBootK8sVolumePvcApplication>(*args)
}
