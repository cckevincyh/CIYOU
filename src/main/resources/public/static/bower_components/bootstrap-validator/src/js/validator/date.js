(function($) {
    $.fn.bootstrapValidator.validators.date = {
        html5Attributes: {
            message: 'message',
            format: 'format'
        },

        /**
         * Return true if the input value is valid date
         *
         * @param {BootstrapValidator} validator The validator plugin instance
         * @param {jQuery} $field Field element
         * @param {Object} options Can consist of the following keys:
         * - message: The invalid message
         * - format: The date format. Default is MM/DD/YYYY
         * The format can be:
         *
         * i) date: Consist of DD, MM, YYYY parts which are separated by /
         * ii) date and time:
         * The time can consist of h, m, s parts which are separated by :
         * ii) date, time and A (indicating AM or PM)
         * @returns {Boolean}
         */
        validate: function(validator, $field, options) {
            var value = $field.val();
            if (value == '') {
                return true;
            }

            options.format = options.format || 'MM/DD/YYYY';

            var formats    = options.format.split(' '),
                dateFormat = formats[0],
                timeFormat = (formats.length > 1) ? formats[1] : null,
                amOrPm     = (formats.length > 2) ? formats[2] : null,
                sections   = value.split(' '),
                date       = sections[0],
                time       = (sections.length > 1) ? sections[1] : null;

            if (formats.length != sections.length) {
                return false;
            }

            // Determine the separator
            var separator = (date.indexOf('/') != -1) ? '/' : ((date.indexOf('-') != -1) ? '-' : null);
            if (separator == null) {
                return false;
            }

            // Determine the date
            date       = date.split(separator);
            dateFormat = dateFormat.split(separator);
            var year  = date[dateFormat.indexOf('YYYY')],
                month = date[dateFormat.indexOf('MM')],
                day   = date[dateFormat.indexOf('DD')];

            // Determine the time
            var minutes = null, hours = null, seconds = null;
            if (timeFormat) {
                timeFormat = timeFormat.split(':'),
                time       = time.split(':');

                if (timeFormat.length != time.length) {
                    return false;
                }

                hours   = time.length > 0 ? time[0] : null;
                minutes = time.length > 1 ? time[1] : null;
                seconds = time.length > 2 ? time[2] : null;

                // Validate seconds
                if (seconds) {
                    seconds = parseInt(seconds, 10);
                    if (seconds < 0 || seconds > 60) {
                        return false;
                    }
                }

                // Validate hours
                if (hours) {
                    hours = parseInt(hours, 10);
                    if (hours < 0 || hours >= 24 || (amOrPm && hours > 12)) {
                        return false;
                    }
                }

                // Validate minutes
                if (minutes) {
                    minutes = parseInt(minutes, 10);
                    if (minutes < 0 || minutes > 59) {
                        return false;
                    }
                }
            }

            // Validate day, month, and year
            day   = parseInt(day, 10);
            month = parseInt(month, 10);
            year  = parseInt(year, 10);

            return $.fn.bootstrapValidator.helpers.date(year, month, day);
        }
    };
}(window.jQuery));
