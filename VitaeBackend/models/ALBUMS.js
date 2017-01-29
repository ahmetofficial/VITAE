/* jshint indent: 2 */

module.exports = function(sequelize, DataTypes) {
  return sequelize.define('ALBUMS', {
    album_id: {
      type: DataTypes.INTEGER(11),
      allowNull: false,
      primaryKey: true,
      autoIncrement: true
    },
    album_name: {
      type: DataTypes.STRING,
      allowNull: false
    },
    created_time: {
      type: DataTypes.DATE,
      allowNull: false
    },
    updated_time: {
      type: DataTypes.DATE,
      allowNull: false
    },
    description: {
      type: DataTypes.STRING,
      allowNull: false
    },
    location_path: {
      type: DataTypes.STRING,
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
    subject_id: {
      type: DataTypes.INTEGER(11),
      allowNull: true
    }
  }, {
    tableName: 'ALBUMS'
  });
};
