/* jshint indent: 2 */

module.exports = function(sequelize, DataTypes) {
  return sequelize.define('EVENTS', {
    event_id: {
      type: DataTypes.INTEGER(11),
      allowNull: false,
      primaryKey: true,
      autoIncrement: true
    },
    event_type_id: {
      type: DataTypes.INTEGER(11),
      allowNull: false,
      references: {
        model: 'EVENT_TYPE',
        key: 'event_type_id'
      }
    },
    subject_id: {
      type: DataTypes.INTEGER(11),
      allowNull: false
    },
    event_name: {
      type: DataTypes.STRING,
      allowNull: false
    },
    event_description: {
      type: DataTypes.STRING,
      allowNull: false
    },
    event_date: {
      type: DataTypes.DATE,
      allowNull: false
    },
    event_create_time: {
      type: DataTypes.TIME,
      allowNull: false,
      defaultValue: sequelize.literal('CURRENT_TIMESTAMP')
    },
    participation_count: {
      type: DataTypes.INTEGER(11),
      allowNull: false
    },
    owner_id: {
      type: DataTypes.STRING,
      allowNull: false,
      references: {
        model: 'USERS',
        key: 'user_id'
      }
    },
    event_photo_id: {
      type: DataTypes.INTEGER(11),
      allowNull: false,
      references: {
        model: 'PHOTOS',
        key: 'photo_id'
      }
    },
    event_general_rate: {
      type: "DOUBLE(10,5)",
      allowNull: false
    }
  }, {
    tableName: 'EVENTS'
  });
};
