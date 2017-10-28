package com.linyuzai.mvp.theme

/**
 * Created by linyuzai on 2017/10/13.
 * @author linyuzai
 */
object MicroThemeManager {
    private val themes: MutableList<MicroTheme> = arrayListOf()
    private val listeners: MutableList<MicroThemeChangeable> = arrayListOf()
    private var store: ((Int) -> Unit)? = null
    private var restore: (() -> Int)? = null
    private lateinit var currentTheme: MicroTheme
    private var currentThemeIndex: Int = 0

    fun init() {
        currentThemeIndex = restore?.invoke() ?: 0
        currentTheme = themes[currentThemeIndex]
    }

    fun onStore(handler: (Int) -> Unit): MicroThemeManager = apply {
        this.store = handler
    }

    fun onRestore(handler: () -> Int): MicroThemeManager = apply {
        this.restore = handler
    }

    fun addTheme(theme: MicroTheme) = themes.add(theme)

    fun addThemes(vararg themes: MicroTheme) = themes.forEach { addTheme(it) }

    fun changeTheme(index: Int) {
        currentThemeIndex = index
        currentTheme = themes[currentThemeIndex]
        listeners.forEach { it.onThemeChange(currentTheme) }
        store?.invoke(currentThemeIndex)
    }

    fun getCurrentTheme(): MicroTheme = currentTheme

    fun getCurrentThemeIndex(): Int = currentThemeIndex

    fun getThemes(): List<MicroTheme> = themes

    fun register(listener: MicroThemeChangeable) = listeners.add(listener)

    fun unregister(listener: MicroThemeChangeable) = listeners.remove(listener)
}