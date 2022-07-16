package com.legyver.fenxlib.icons.standard;

import com.legyver.fenxlib.api.icons.options.IconOptions;

/**
 * IconOptions specific to IcoMoon
 */
public class IcoMoonIconOptions extends IconOptions {

    private final IcoMoonFontEnum icoMoonFont;

    private IcoMoonIconOptions(IcoMoonFontEnum icoMoonFont) {
        super();
        this.icoMoonFont = icoMoonFont;
    }

    @Override
    public String getFamily() {
        return IcoMoonFontData.FAMILY_NAME;
    }

    @Override
    public String getIcon() {
        if (icoMoonFont != null) {
            return icoMoonFont.getGlyphName();
        }
        return super.getIcon();
    }

    /**
     * Get the IcoMoon enum value.
     * @return the enum value
     */
    public IcoMoonFontEnum getIcoMoonFont() {
        return icoMoonFont;
    }

    /**
     * Builder for producing IcoMoon IconOptions
     */
    public static class Builder extends IconOptions.Builder<Builder, IcoMoonIconOptions> {
        private IcoMoonFontEnum icoMoonFont;

        /**
         * Specify an icon to use
         * @param icon the enum value for the icon
         * @return this builder
         */
        public Builder icoMoonIcon(IcoMoonFontEnum icon) {
            this.icoMoonFont = icon;
            return this;
        }

        protected IcoMoonIconOptions buildInternal() {
            return new IcoMoonIconOptions(icoMoonFont);
        }

    }
}
