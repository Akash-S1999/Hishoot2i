@file:Suppress("NOTHING_TO_INLINE")

package rbb.hishoot2i.common.ext

import java.io.InputStream
import java.util.zip.ZipFile

inline fun ZipFile.entryInputStream(entryName: String): InputStream =
    getEntry(entryName).let { getInputStream(it) }